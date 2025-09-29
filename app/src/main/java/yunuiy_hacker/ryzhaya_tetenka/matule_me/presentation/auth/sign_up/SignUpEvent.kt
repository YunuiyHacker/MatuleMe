package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.sign_up

import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.sign_in.SignInEvent

sealed class SignUpEvent {
    data class ChangeNameEvent(val name: String) : SignUpEvent()
    data class ChangeEmailEvent(val email: String) : SignUpEvent()
    data class ChangePasswordEvent(val password: String) : SignUpEvent()
    data object TogglePasswordVisibilityEvent : SignUpEvent()
    data object TogglePrivacyPolicyConfirmEvent : SignUpEvent()

    data class ShowMessageDialogEvent(val message: String?) : SignUpEvent()
    data object HideMessageDialogEvent : SignUpEvent()

    data object ShowInternetIsNotAvailableDialogEvent : SignUpEvent()
    data object HideInternetIsNotAvailableDialogEvent : SignUpEvent()

    data object OnClickButtonEvent : SignUpEvent()
}