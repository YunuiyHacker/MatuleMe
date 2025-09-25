package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.sign_in

sealed class SignInEvent {
    data class ChangeEmailEvent(val email: String) : SignInEvent()
    data class ChangePasswordEvent(val password: String) : SignInEvent()
    data object TogglePasswordVisibilityEvent : SignInEvent()

    data class ShowMessageDialogEvent(val message: String?) : SignInEvent()
    data object HideMessageDialogEvent : SignInEvent()

    data object OnClickButtonEvent : SignInEvent()
}