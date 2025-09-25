package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.forgot_password

sealed class ForgotPasswordEvent {
    data class ChangeEmailEvent(val email: String) : ForgotPasswordEvent()

    data object ShowCheckYourEmailDialogEvent : ForgotPasswordEvent()
    data object HideCheckYourEmailDialogEvent : ForgotPasswordEvent()

    data class ShowMessageDialogEvent(val message: String?) : ForgotPasswordEvent()
    data object HideMessageDialogEvent : ForgotPasswordEvent()

    data object OnClickButtonEvent : ForgotPasswordEvent()
}