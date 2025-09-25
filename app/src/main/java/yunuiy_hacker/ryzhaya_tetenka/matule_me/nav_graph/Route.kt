package yunuiy_hacker.ryzhaya_tetenka.matule_me.nav_graph

sealed class Route(val route: String) {
    data object OnboardingScreen : Route("onboardingScreen")
    data object SignInScreen : Route("signInScreen")
    data object PdfViewerScreen : Route("pdfViewerScreen")
    data object SignUpScreen : Route("signUpScreen")
    data object ForgotPasswordScreen : Route("forgotPasswordScreen")
    data object OTPVerificationScreen : Route("otpVerificationScreen")
    data object NewPasswordScreen : Route("newPasswordScreen")
    data object HomeScreen : Route("homeScreen")
}
