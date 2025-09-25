package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.forgot_password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.forgot_password.SendRequestForTakeOTPCodeUseCase
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(private val sendRequestForTakeOTPCodeUseCase: SendRequestForTakeOTPCodeUseCase) :
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

            is ForgotPasswordEvent.OnClickButtonEvent -> forgotPassword()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun forgotPassword() {
        state.contentState.isLoading.value = true

        GlobalScope.launch(Dispatchers.IO) {
            runBlocking {
                try {
                    sendRequestForTakeOTPCodeUseCase.execute(email = state.email)

                    state.showCheckYourEmailDialog = true
                    state.contentState.isLoading.value = false
                } catch (e: Exception) {
                    state.contentState.isLoading.value = false

                    state.message = e.message.toString()
                    state.showMessageDialog = true
                }
            }
        }
    }
}