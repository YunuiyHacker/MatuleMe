package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.main.profile

import android.R.attr.navigationIcon
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import yunuiy_hacker.ryzhaya_tetenka.matule_me.R
import yunuiy_hacker.ryzhaya_tetenka.matule_me.nav_graph.Route
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.common.composable.bottom_navigation_bar.BottomNavBar
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.main.home.HomeEvent
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.BlockBackgroundColor
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.raleway

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navHostController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel<ProfileViewModel>()
) {

    LaunchedEffect(Unit) {
        viewModel.onEvent(ProfileEvent.LoadDataEvent)
    }

    viewModel.state.let { state ->
        Scaffold(bottomBar = {
            BottomNavBar(
                selectedRoute = navHostController.currentBackStackEntry?.destination?.route!!,
                onChangeRoute = {
                    navHostController.navigate(it)
                })
        }, topBar = {
            TopAppBar(
                modifier = Modifier.padding(horizontal = 20.dp),
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier.offset(x = -24.dp),
                            text = stringResource(R.string.profile),
                            fontFamily = raleway,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            lineHeight = 20.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(color = BlockBackgroundColor)
                            .clickable {
                                navHostController.popBackStack(
                                    Route.HomeScreen.route, inclusive = false
                                )
                            }) {
                        Icon(
                            modifier = Modifier.padding(10.dp),
                            painter = painterResource(R.drawable.ic_back),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                })
        }, containerColor = MaterialTheme.colorScheme.background) {
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