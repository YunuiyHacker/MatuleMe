package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.otp_verification

import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.sign_in.SignInEvent
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.sign_up.SignUpEvent

sealed class OTPVerificationEvent {
    data object LoadDataEvent : OTPVerificationEvent()

    data object ResendOTPEvent : OTPVerificationEvent()
    data class ValidateOTPEvent(val code: String) : OTPVerificationEvent()

    data object ShowInternetIsNotAvailableDialogEvent : OTPVerificationEvent()
    data object HideInternetIsNotAvailableDialogEvent : OTPVerificationEvent()


    data object ShowServerIsNotAvailableDialogEvent : OTPVerificationEvent()
    data object HideServerIsNotAvailableDialogEvent : OTPVerificationEvent()
}