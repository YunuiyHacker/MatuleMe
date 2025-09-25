package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.otp_verification

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OTPVerificationViewModel @Inject constructor() : ViewModel() {
    val state by mutableStateOf(OTPVerificationState())

    fun onEvent(event: OTPVerificationEvent) {

    }
}