package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.sign_in

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import yunuiy_hacker.ryzhaya_tetenka.matule_me.R
import yunuiy_hacker.ryzhaya_tetenka.matule_me.data.common.model.User
import yunuiy_hacker.ryzhaya_tetenka.matule_me.data.local.shared_prefs.SharedPrefsHelper
import yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.common.mappers.toDomain
import yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.sign_in.SignInUseCase
import yunuiy_hacker.ryzhaya_tetenka.matule_me.utils.Constants.SUCCESS_DIALOG_SHOW_TIME_IN_SECONDS
import yunuiy_hacker.ryzhaya_tetenka.matule_me.utils.InternetUtils
import yunuiy_hacker.ryzhaya_tetenka.matule_me.utils.RegexPatterns.PATTERN_EMAIL
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class SignInViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val signInUseCase: SignInUseCase,
    private val sharedPrefsHelper: SharedPrefsHelper
) : ViewModel() {
    val state by mutableStateOf(SignInState())

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.ChangeEmailEvent -> {
                state.email = event.email
            }

            is SignInEvent.ChangePasswordEvent -> {
                state.password = event.password
            }

            is SignInEvent.TogglePasswordVisibilityEvent -> {
                state.passwordIsVisible = !state.passwordIsVisible
            }

            is SignInEvent.ShowMessageDialogEvent -> {
                state.showMessageDialog = true
                if (!event.message.isNullOrEmpty()) state.message = event.message
            }

            is SignInEvent.HideMessageDialogEvent -> {
                state.showMessageDialog = false
                state.message = ""
            }

            is SignInEvent.ShowInternetIsNotAvailableDialogEvent -> {
                state.contentState.internetIsAvailable.value = false
            }

            is SignInEvent.HideInternetIsNotAvailableDialogEvent -> {
                state.contentState.internetIsAvailable.value = true
            }

            is SignInEvent.OnClickButtonEvent -> signIn()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun signIn() {
        state.contentState.isLoading.value = true

        GlobalScope.launch(Dispatchers.IO) {
            runBlocking {
                try {
                    validate()

                    state.contentState.internetIsAvailable.value =
                        InternetUtils.isInternetAvailable(context)

                    if (state.contentState.internetIsAvailable.value) {
                        if (state.emailIsValid && state.passwordIsValid) {
                            val user = signInUseCase.execute(
                                User(
                                    email = state.email, password = state.password
                                )
                            )?.toDomain()

                            if (user != null) {
                                sharedPrefsHelper.userId = user.id
                                sharedPrefsHelper.userName = user.name
                                sharedPrefsHelper.userSurname = user.surname

                                state.showSuccessDialog = true
                                delay(SUCCESS_DIALOG_SHOW_TIME_IN_SECONDS.seconds)
                                state.showSuccessDialog = false

                                state.success = true
                            } else {
                                state.message =
                                    context.getString(R.string.in_system_not_user_with_this_credits)
                                state.showMessageDialog = true
                            }

                        } else {
                            state.showMessageDialog = true
                        }
                    }

                    state.contentState.isLoading.value = false
                } catch (e: Exception) {
                    state.contentState.isLoading.value = false

                    state.message =
                        context.getString(R.string.an_unknown_error_occurred_during_the_operation)
                    state.showMessageDialog = true
                }
            }
        }
    }

    private fun validate() {
        if (!PATTERN_EMAIL.matcher(state.email).matches()) {
            state.emailIsValid = false
            state.message = context.getString(R.string.incorrect_format_of_email)
        } else state.emailIsValid = true
        if (state.password.length < 8) {
            state.passwordIsValid = false
            state.message = context.getString(R.string.min_length_of_password_is_8_symbols)
        } else state.passwordIsValid = true
    }
}