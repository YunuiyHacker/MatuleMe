package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.new_pasword

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
import yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.change_password.ChangePasswordUseCase
import yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.forgot_password.SendRequestForTakeOTPCodeUseCase
import javax.inject.Inject

@HiltViewModel
class NewPasswordViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val changePasswordUseCase: ChangePasswordUseCase
) :
    ViewModel() {
    val state by mutableStateOf(NewPasswordState())

    fun onEvent(event: NewPasswordEvent) {
        when (event) {
            is NewPasswordEvent.ChangePasswordEvent -> {
                state.password = event.password
            }

            is NewPasswordEvent.ChangePasswordConfirmationEvent -> {
                state.passwordConfirmation = event.passwordConfirmation
            }

            is NewPasswordEvent.TogglePasswordVisibilityEvent -> {
                state.passwordIsVisible = !state.passwordIsVisible
            }

            is NewPasswordEvent.TogglePasswordConfirmationVisibilityEvent -> {
                state.passwordConfirmationIsVisible = !state.passwordConfirmationIsVisible
            }

            is NewPasswordEvent.ShowMessageDialogEvent -> {
                state.showMessageDialog = true
                if (!event.message.isNullOrEmpty()) state.message = event.message
            }

            is NewPasswordEvent.HideMessageDialogEvent -> {
                state.showMessageDialog = false
                state.message = ""
            }

            is NewPasswordEvent.OnClickButtonEvent -> changePassword()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun changePassword() {
        state.contentState.isLoading.value = true

        GlobalScope.launch(Dispatchers.IO) {
            runBlocking {
                try {
                    validate()

                    if (state.passwordIsValid && state.passwordConfirmationIsValid && state.password == state.passwordConfirmation) {
                        changePasswordUseCase.execute(
                            email = state.email,
                            newPassword = state.password
                        )
                    } else {
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
        if (state.password.length < 8) {
            state.passwordIsValid = false
            state.message = context.getString(R.string.min_length_of_password_is_8_symbols)
        } else state.passwordIsValid = true
        if (state.passwordConfirmation.length < 8) {
            state.passwordConfirmationIsValid = false
            state.message =
                context.getString(R.string.min_length_of_password_confirmation_is_8_symbols)
        } else state.passwordConfirmationIsValid = true
        if (state.password == state.passwordConfirmation) {
            state.message = context.getString(R.string.password_and_its_confirmation_must_match)
        }
    }
}