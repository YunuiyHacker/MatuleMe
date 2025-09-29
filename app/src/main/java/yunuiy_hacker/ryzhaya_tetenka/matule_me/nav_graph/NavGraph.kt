package yunuiy_hacker.ryzhaya_tetenka.matule_me.nav_graph

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.forgot_password.ForgotPasswordScreen
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.new_pasword.NewPasswordScreen
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.new_pasword.NewPasswordViewModel
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.main.home.HomeScreen
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.onboarding.OnboardingScreen
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.otp_verification.OTPVerificationScreen
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.otp_verification.OTPVerificationViewModel
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.sign_in.SignInScreen
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.sign_up.SignUpScreen
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.sign_up.pdf.PdfViewerScreen
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.main.profile.ProfileScreen

@Composable
fun NavGraph(navHostController: NavHostController, startDestination: String) {
    NavHost(navController = navHostController, startDestination = startDestination) {
        composable(route = Route.OnboardingScreen.route) {
            OnboardingScreen(navHostController = navHostController)
        }
        composable(route = Route.SignInScreen.route) {
            SignInScreen(navHostController = navHostController)
        }
        composable(route = Route.PdfViewerScreen.route) {
            PdfViewerScreen(navHostController = navHostController)
        }
        composable(route = Route.SignUpScreen.route) {
            SignUpScreen(navHostController = navHostController)
        }
        composable(route = Route.ForgotPasswordScreen.route) {
            ForgotPasswordScreen(navHostController = navHostController)
        }
        composable(
            route = "${Route.OTPVerificationScreen.route}/{email}", arguments = listOf(
                navArgument("email") {
                    NavType.StringType
                    nullable = false
                })
        ) {
            val email = it.arguments?.getString("email").toString()

            val viewModel: OTPVerificationViewModel = hiltViewModel()
            viewModel.state.email = email

            OTPVerificationScreen(navHostController = navHostController, viewModel = viewModel)
        }
        composable(
            route = "${Route.NewPasswordScreen.route}/{email}", arguments = listOf(
                navArgument("email") {
                    NavType.StringType
                    nullable = false
                })
        ) {
            val email = it.arguments?.getString("email").toString()

            val viewModel: NewPasswordViewModel = hiltViewModel()
            viewModel.state.email = email

            NewPasswordScreen(navHostController = navHostController, viewModel = viewModel)
        }

        composable(route = Route.NewPasswordScreen.route) {
            NewPasswordScreen(navHostController = navHostController)
        }

        //Main screens
        composable(route = Route.HomeScreen.route) {
            HomeScreen(navHostController = navHostController)
        }
        composable(route = Route.FavoriteScreen.route) {

        }
        composable(route = Route.NotificationScreen.route) {
        }
        composable(route = Route.ProfileScreen.route) {
            ProfileScreen(navHostController = navHostController)
        }
    }
}