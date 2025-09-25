package yunuiy_hacker.ryzhaya_tetenka.matule_me

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import yunuiy_hacker.ryzhaya_tetenka.matule_me.data.local.shared_prefs.SharedPrefsHelper
import yunuiy_hacker.ryzhaya_tetenka.matule_me.nav_graph.NavGraph
import yunuiy_hacker.ryzhaya_tetenka.matule_me.nav_graph.Route
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.MatuleMeTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var sharedPrefsHelper: SharedPrefsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val onboardingIsPassed = sharedPrefsHelper.onboardingIsPassed
        val userIsAuthorized = sharedPrefsHelper.userId != 0

        setContent {
            val navHostController = rememberNavController()

            MatuleMeTheme {
                NavGraph(
                    navHostController = navHostController,
                    startDestination = if (userIsAuthorized) Route.HomeScreen.route else {
                        if (onboardingIsPassed) Route.SignInScreen.route else Route.OnboardingScreen.route
                    }
                )
            }
        }
    }
}