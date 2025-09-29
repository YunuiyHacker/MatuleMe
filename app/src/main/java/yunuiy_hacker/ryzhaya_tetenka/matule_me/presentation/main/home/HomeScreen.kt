package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.main.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.common.composable.bottom_navigation_bar.BottomNavBar

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel<HomeViewModel>()
) {

    LaunchedEffect(Unit) {
        viewModel.onEvent(HomeEvent.LoadDataEvent)
    }

    viewModel.state.let { state ->
        Scaffold(bottomBar = {
            BottomNavBar(
                selectedRoute = navHostController.currentBackStackEntry?.destination?.route!!,
                onChangeRoute = {
                    navHostController.navigate(it)
                })
        }, containerColor = Color.Unspecified) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {

            }
        }

        BackHandler {

        }
    }
}