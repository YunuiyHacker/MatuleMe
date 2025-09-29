package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.forgot_password

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import yunuiy_hacker.ryzhaya_tetenka.matule_me.R
import yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.common.use_case.CheckingEmailForRegistrationOperator
import yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.forgot_password.SendRequestForTakeOTPCodeUseCase
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.sign_up.SignUpEvent
import yunuiy_hacker.ryzhaya_tetenka.matule_me.utils.InternetUtils
import yunuiy_hacker.ryzhaya_tetenka.matule_me.utils.RegexPatterns
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val checkingEmailForRegistrationOperator: CheckingEmailForRegistrationOperator,
    private val sendRequestForTakeOTPCodeUseCase: SendRequestForTakeOTPCodeUseCase
) :
    ViewModel() {
    val state by mutableStateOf(ForgotPasswordState())

    fun onEvent(event: ForgotPasswordEvent) {
        when (event) {
            is ForgotPasswordEvent.ChangeEmailEvent -> {
                state.email = event.email
            }

            is ForgotPasswordEvent.ShowCheckYourEmailDialogEvent -> {
                state.showCheckYourEmailDialog = true
            }

            is ForgotPasswordEvent.HideCheckYourEmailDialogEvent -> {
                state.showCheckYourEmailDialog = false
                state.success = true
            }

            is ForgotPasswordEvent.ShowMessageDialogEvent -> {
                state.showMessageDialog = true
                if (!event.message.isNullOrEmpty()) state.message = event.message
            }

            is ForgotPasswordEvent.HideMessageDialogEvent -> {
                state.showMessageDialog = false
                state.message = ""
            }

            is ForgotPasswordEvent.ShowInternetIsNotAvailableDialogEvent -> {
                state.contentState.internetIsAvailable.value = false
            }

            is ForgotPasswordEvent.HideInternetIsNotAvailableDialogEvent -> {
                state.contentState.internetIsAvailable.value = true
            }

            is ForgotPasswordEvent.OnClickButtonEvent -> forgotPassword()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun forgotPassword() {
        state.contentState.isLoading.value = true

        GlobalScope.launch(Dispatchers.IO) {
            runBlocking {
                try {
                    validate()

                    state.contentState.internetIsAvailable.value =
                        InternetUtils.isInternetAvailable(context)

                    if (state.contentState.internetIsAvailable.value) {
                        if (state.emailIsValid) {
                            if (checkingEmailForRegistrationOperator.invoke(email = state.email)) {

                                sendRequestForTakeOTPCodeUseCase.execute(email = state.email)

                                state.showCheckYourEmailDialog = true
                            } else {
                                state.message =
                                    context.getString(R.string.in_system_not_user_with_this_email)
                                state.showMessageDialog = true
                            }
                        } else
                            state.showMessageDialog = true
                    }

                    state.contentState.isLoading.value = false
                } catch (e: Exception) {
                    state.contentState.isLoading.value = false

                    state.message = e.message.toString()
                    state.showMessageDialog = true
                }
            }
        }
    }

    private fun validate() {
        if (RegexPatterns.PATTERN_EMAIL.matcher(state.email).matches()) {
            state.emailIsValid = true
            state.message = context.getString(R.string.incorrect_format_of_email)
        } else state.emailIsValid = false
    }
}