package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.otp_verification

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.change_password.CheckingOTPCodeAccuracyUseCase
import yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.change_password.ResendOTPCodeUseCase
import yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.common.use_case.CheckingServerAvailableUseCase
import yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.forgot_password.SendRequestForTakeOTPCodeUseCase
import yunuiy_hacker.ryzhaya_tetenka.matule_me.utils.Constants.OTP_CODE_RESEND_TIME_IN_SECONDS
import yunuiy_hacker.ryzhaya_tetenka.matule_me.utils.Constants.SUCCESS_DIALOG_SHOW_TIME_IN_SECONDS
import yunuiy_hacker.ryzhaya_tetenka.matule_me.utils.NetworkUtils
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class OTPVerificationViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val checkingServerAvailableUseCase: CheckingServerAvailableUseCase,
    private val sendRequestForTakeOTPCodeUseCase: SendRequestForTakeOTPCodeUseCase,
    private val resendOTPCodeUseCase: ResendOTPCodeUseCase,
    private val checkingOTPCodeAccuracyUseCase: CheckingOTPCodeAccuracyUseCase
) : ViewModel() {
    val state by mutableStateOf(OTPVerificationState())

    fun onEvent(event: OTPVerificationEvent) {
        when (event) {
            is OTPVerificationEvent.LoadDataEvent -> loadData()

            is OTPVerificationEvent.ResendOTPEvent -> resendOTP()
            is OTPVerificationEvent.ValidateOTPEvent -> validateOTP(event.code)


            is OTPVerificationEvent.ShowInternetIsNotAvailableDialogEvent -> {
                state.contentState.internetIsAvailable.value = false
            }

            is OTPVerificationEvent.HideInternetIsNotAvailableDialogEvent -> {
                state.contentState.internetIsAvailable.value = true
            }

            is OTPVerificationEvent.ShowServerIsNotAvailableDialogEvent -> {
                state.contentState.serverIsAvailable.value = false
            }

            is OTPVerificationEvent.HideServerIsNotAvailableDialogEvent -> {
                state.contentState.serverIsAvailable.value = true
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun loadData() {
        GlobalScope.launch(Dispatchers.IO) {
            startTimer()
        }

        state.contentState.isLoading.value = true

        GlobalScope.launch(Dispatchers.IO) {
            runBlocking {
                try {

                    state.contentState.isLoading.value = false
                } catch (e: Exception) {
                    state.contentState.isLoading.value = false
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun resendOTP() {
        GlobalScope.launch(Dispatchers.IO) {
            startTimer()
        }

        state.contentState.isLoading.value = true

        GlobalScope.launch(Dispatchers.IO) {
            runBlocking {
                try {

                    state.contentState.internetIsAvailable.value =
                        NetworkUtils.isInternetAvailable(context)

                    state.contentState.serverIsAvailable.value =
                        checkingServerAvailableUseCase.execute()

                    if (state.contentState.internetIsAvailable.value && state.contentState.serverIsAvailable.value) {
                        sendRequestForTakeOTPCodeUseCase.execute(state.email)
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

    @OptIn(DelicateCoroutinesApi::class)
    private fun validateOTP(code: String) {
        state.contentState.isLoading.value = true

        GlobalScope.launch(Dispatchers.IO) {
            runBlocking {
                try {
                    state.contentState.internetIsAvailable.value =
                        NetworkUtils.isInternetAvailable(context)

                    state.contentState.serverIsAvailable.value =
                        checkingServerAvailableUseCase.execute()

                    if (state.contentState.internetIsAvailable.value && state.contentState.serverIsAvailable.value) {
                        val checkingResult = checkingOTPCodeAccuracyUseCase.execute(
                            email = state.email, token = code
                        )
                        if (checkingResult.isSuccess) {
                            state.OTPCodeIsValid = checkingResult.getOrNull() ?: false

                            if (state.OTPCodeIsValid) {
                                state.contentState.isLoading.value = false

                                state.showSuccessDialog = true
                                delay(SUCCESS_DIALOG_SHOW_TIME_IN_SECONDS.seconds)
                                state.showSuccessDialog = false
                                state.success = true
                            } else {
                                state.contentState.isLoading.value = false

                                state.invalidOTPCode = true
                                delay(5.seconds)
                                state.invalidOTPCode = false
                            }
                        } else if (checkingResult.isFailure) {
                            state.contentState.isLoading.value = false


                            state.message = checkingResult.exceptionOrNull()?.message!!
                            state.showMessageDialog = true
                        }
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

    private suspend fun startTimer() {
        state.time = OTP_CODE_RESEND_TIME_IN_SECONDS
        while (state.time > 0) {
            state.time -= 1
            delay(1.seconds)
        }
    }
}