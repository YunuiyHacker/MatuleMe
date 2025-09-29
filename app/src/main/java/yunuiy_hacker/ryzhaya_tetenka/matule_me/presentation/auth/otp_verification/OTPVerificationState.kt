package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.otp_verification

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.common.state.ContentState

class OTPVerificationState {
    var email by mutableStateOf("")

    var OTPCodeIsValid by mutableStateOf(false)
    var invalidOTPCode by mutableStateOf(false)

    var time by mutableStateOf(0)

    var showCheckYourEmailDialog by mutableStateOf(false)

    var message by mutableStateOf("")
    var showMessageDialog by mutableStateOf(false)
    var showSuccessDialog by mutableStateOf(false)

    var contentState by mutableStateOf(ContentState())

    var success by mutableStateOf(false)
}