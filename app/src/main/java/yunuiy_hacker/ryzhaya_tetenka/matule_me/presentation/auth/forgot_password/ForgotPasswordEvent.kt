package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.forgot_password

import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.sign_in.SignInEvent
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.sign_up.SignUpEvent

sealed class ForgotPasswordEvent {
    data class ChangeEmailEvent(val email: String) : ForgotPasswordEvent()

    data object ShowCheckYourEmailDialogEvent : ForgotPasswordEvent()
    data object HideCheckYourEmailDialogEvent : ForgotPasswordEvent()

    data class ShowMessageDialogEvent(val message: String?) : ForgotPasswordEvent()
    data object HideMessageDialogEvent : ForgotPasswordEvent()

    data object ShowInternetIsNotAvailableDialogEvent : ForgotPasswordEvent()
    data object HideInternetIsNotAvailableDialogEvent : ForgotPasswordEvent()


    data object ShowServerIsNotAvailableDialogEvent : ForgotPasswordEvent()
    data object HideServerIsNotAvailableDialogEvent : ForgotPasswordEvent()

    data object OnClickButtonEvent : ForgotPasswordEvent()
}