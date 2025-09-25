package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch
import yunuiy_hacker.ryzhaya_tetenka.matule_me.R
import yunuiy_hacker.ryzhaya_tetenka.matule_me.nav_graph.Route
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.onboarding.pages.OnboardingFirstPage
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.onboarding.pages.OnboardingSecondPage
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.onboarding.pages.OnboardingThirdPage
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.DisableColor
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.raleway

@OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
@Composable
fun OnboardingScreen(
    navHostController: NavHostController,
    viewModel: OnboardingViewModel = hiltViewModel<OnboardingViewModel>()
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()

    viewModel.state.let { state ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(
                                0xFF48B2E7
                            ), Color(0xFF0076B1)
                        )
                    )
                )
        ) {
            Scaffold(bottomBar = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    Button(
                        modifier = Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = MaterialTheme.colorScheme.onSurface
                        ),
                        onClick = {
                            if (pagerState.currentPage < pagerState.pageCount - 1) {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }
                            } else {
                                viewModel.onEvent(OnboardingEvent.OnboardingPassedChangeEvent)
                            }
                        }) {
                        Text(
                            modifier = Modifier.padding(vertical = 10.dp),
                            text = if (pagerState.currentPage == 0) stringResource(R.string.to_start) else stringResource(
                                R.string.farther
                            ),
                            fontFamily = raleway,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.height(36.dp))
                }
            }, containerColor = Color.Transparent) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        HorizontalPager(state = pagerState) {
                            key(it) {
                                when (it) {
                                    0 -> OnboardingFirstPage()
                                    1 -> OnboardingSecondPage()
                                    2 -> OnboardingThirdPage()
                                }
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .offset(y = -90.dp), horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(3) {
                            if (it == pagerState.currentPage) {
                                Box(
                                    modifier = Modifier
                                        .width(43.dp)
                                        .height(6.dp)
                                        .background(color = Color.White, shape = CircleShape)
                                )
                            } else {
                                Box(
                                    modifier = Modifier
                                        .width(28.dp)
                                        .height(6.dp)
                                        .background(color = DisableColor, shape = CircleShape)
                                )
                            }
                            if (it < 3 - 1) {
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                        }
                    }
                }
            }
        }

        LaunchedEffect(state.success) {
            if (state.success == true) {
                navHostController.navigate(Route.SignInScreen.route)
            }
        }
    }
}