package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.new_pasword

sealed class NewPasswordEvent {
    data class ChangePasswordEvent(val password: String) : NewPasswordEvent()
    data class ChangePasswordConfirmationEvent(val passwordConfirmation: String) :
        NewPasswordEvent()

    data object TogglePasswordVisibilityEvent : NewPasswordEvent()
    data object TogglePasswordConfirmationVisibilityEvent : NewPasswordEvent()

    data class ShowMessageDialogEvent(val message: String?) : NewPasswordEvent()
    data object HideMessageDialogEvent : NewPasswordEvent()

    data object OnClickButtonEvent : NewPasswordEvent()
}