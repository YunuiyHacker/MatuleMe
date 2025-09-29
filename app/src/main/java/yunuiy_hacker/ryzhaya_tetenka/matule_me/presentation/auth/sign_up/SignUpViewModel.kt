package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.sign_up

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
import yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.common.use_case.CheckingServerAvailableUseCase
import yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.sign_up.SignUpUseCase
import yunuiy_hacker.ryzhaya_tetenka.matule_me.utils.Constants.SUCCESS_DIALOG_SHOW_TIME_IN_SECONDS
import yunuiy_hacker.ryzhaya_tetenka.matule_me.utils.NetworkUtils
import yunuiy_hacker.ryzhaya_tetenka.matule_me.utils.RegexPatterns.PATTERN_EMAIL
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class SignUpViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val checkingServerAvailableUseCase: CheckingServerAvailableUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val sharedPrefsHelper: SharedPrefsHelper
) : ViewModel() {
    val state by mutableStateOf(SignUpState())

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.ChangeNameEvent -> {
                state.name = event.name
            }

            is SignUpEvent.ChangeEmailEvent -> {
                state.email = event.email
            }

            is SignUpEvent.ChangePasswordEvent -> {
                state.password = event.password
            }

            is SignUpEvent.TogglePasswordVisibilityEvent -> {
                state.passwordIsVisible = !state.passwordIsVisible
            }

            is SignUpEvent.TogglePrivacyPolicyConfirmEvent -> {
                state.privacyPolicyIsConfirmed = !state.privacyPolicyIsConfirmed
            }

            is SignUpEvent.ShowMessageDialogEvent -> {
                state.showMessageDialog = true
                if (!event.message.isNullOrEmpty()) state.message = event.message
            }

            is SignUpEvent.HideMessageDialogEvent -> {
                state.showMessageDialog = false
                state.message = ""
            }

            is SignUpEvent.ShowInternetIsNotAvailableDialogEvent -> {
                state.contentState.internetIsAvailable.value = false
            }

            is SignUpEvent.HideInternetIsNotAvailableDialogEvent -> {
                state.contentState.internetIsAvailable.value = true
            }

            is SignUpEvent.ShowServerIsNotAvailableDialogEvent -> {
                state.contentState.serverIsAvailable.value = false
            }

            is SignUpEvent.HideServerIsNotAvailableDialogEvent -> {
                state.contentState.serverIsAvailable.value = true
            }

            is SignUpEvent.OnClickButtonEvent -> signUp()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun signUp() {
        state.contentState.isLoading.value = true

        GlobalScope.launch(Dispatchers.IO) {
            runBlocking {
                try {
                    validate()

                    state.contentState.internetIsAvailable.value =
                        NetworkUtils.isInternetAvailable(context)

                    state.contentState.serverIsAvailable.value =
                        checkingServerAvailableUseCase.execute()

                    if (state.contentState.internetIsAvailable.value && state.contentState.serverIsAvailable.value) {
                        if (state.nameIsValid && state.emailIsValid && state.passwordIsValid && state.privacyPolicyIsConfirmed) {
                            if (signUpUseCase.checkingEmailForRegistrationOperator(state.email)) {
                                state.message =
                                    context.getString(R.string.user_with_such_an_email_address_is_already_registered_in_the_system)
                                state.showMessageDialog = true
                            } else {
                                val user = signUpUseCase.createNewUserOperator(
                                    User(
                                        name = state.name,
                                        email = state.email,
                                        password = state.password
                                    )
                                )

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
                                        context.getString(R.string.an_unknown_error_occurred_during_the_operation)
                                    state.showMessageDialog = true
                                }
                            }
                        } else {
                            state.showMessageDialog = true
                        }
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
        if (state.name.length < 2) {
            state.message = context.getString(R.string.name_must_be_at_least_2_characters_long)
            state.nameIsValid = false
        } else state.nameIsValid = true
        if (!PATTERN_EMAIL.matcher(state.email).matches()) {
            state.emailIsValid = false
            state.message = context.getString(R.string.incorrect_format_of_email)
        } else state.emailIsValid = true
        if (state.password.length < 8) {
            state.passwordIsValid = false
            state.message = context.getString(R.string.min_length_of_password_is_8_symbols)
        } else state.passwordIsValid = true
        if (!state.privacyPolicyIsConfirmed) {
            state.message = context.getString(R.string.confirm_privacy_policy)
        }
    }
}