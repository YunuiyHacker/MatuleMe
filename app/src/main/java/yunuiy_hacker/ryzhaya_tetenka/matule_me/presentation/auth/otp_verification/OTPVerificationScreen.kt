package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.otp_verification

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import yunuiy_hacker.ryzhaya_tetenka.matule_me.R
import yunuiy_hacker.ryzhaya_tetenka.matule_me.nav_graph.Route
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.common.composable.LoadingDialog
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.forgot_password.composable.CheckYourEmailDialog
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.otp_verification.composable.OTPCodeTextFieldItem
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.BlockBackgroundColor
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.MatuleMeTheme
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.raleway
import yunuiy_hacker.ryzhaya_tetenka.matule_me.utils.FormatUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OTPVerificationScreen(
    navHostController: NavHostController,
    viewModel: OTPVerificationViewModel = hiltViewModel<OTPVerificationViewModel>()
) {
    val focusManager = LocalFocusManager.current

    var otpCodePart1 by remember { mutableStateOf("") }
    var otpCodePart2 by remember { mutableStateOf("") }
    var otpCodePart3 by remember { mutableStateOf("") }
    var otpCodePart4 by remember { mutableStateOf("") }
    var otpCodePart5 by remember { mutableStateOf("") }
    var otpCodePart6 by remember { mutableStateOf("") }


    viewModel.state.let { state ->
        Scaffold(containerColor = MaterialTheme.colorScheme.background, topBar = {
            TopAppBar(
                modifier = Modifier.padding(horizontal = 20.dp),
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
                title = {},
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(color = BlockBackgroundColor)
                            .clickable {
                                navHostController.popBackStack(
                                    Route.ForgotPasswordScreen.route, inclusive = false
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
        }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(11.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.otp_verification),
                    fontSize = 32.sp,
                    fontFamily = raleway,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    text = stringResource(R.string.please_check_your_email_to_see_the_verification_code),
                    fontSize = 16.sp,
                    fontFamily = raleway,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier,
                    text = stringResource(R.string.otp_code),
                    fontSize = 21.sp,
                    fontFamily = raleway,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    OTPCodeTextFieldItem(
                        modifier = Modifier
                            .height(99.dp)
                            .weight(1f),
                        value = otpCodePart1,
                        onValueChange = {
                            otpCodePart1 = it
                            if (it.isNotEmpty()) focusManager.moveFocus(FocusDirection.Next)
                        })
                    Spacer(modifier = Modifier.width(12.dp))
                    OTPCodeTextFieldItem(
                        modifier = Modifier
                            .height(99.dp)
                            .weight(1f),
                        value = otpCodePart2,
                        onValueChange = {
                            otpCodePart2 = it
                            if (it.isNotEmpty()) focusManager.moveFocus(FocusDirection.Next)
                            else focusManager.moveFocus(FocusDirection.Previous)
                        })
                    Spacer(modifier = Modifier.width(12.dp))
                    OTPCodeTextFieldItem(
                        modifier = Modifier
                            .height(99.dp)
                            .weight(1f),
                        value = otpCodePart3,
                        onValueChange = {
                            otpCodePart3 = it
                            if (it.isNotEmpty()) focusManager.moveFocus(FocusDirection.Next)
                            else focusManager.moveFocus(FocusDirection.Previous)
                        })
                    Spacer(modifier = Modifier.width(12.dp))
                    OTPCodeTextFieldItem(
                        modifier = Modifier
                            .height(99.dp)
                            .weight(1f),
                        value = otpCodePart4,
                        onValueChange = {
                            otpCodePart4 = it
                            if (it.isNotEmpty()) focusManager.moveFocus(FocusDirection.Next)
                            else focusManager.moveFocus(FocusDirection.Previous)
                        })
                    Spacer(modifier = Modifier.width(12.dp))
                    OTPCodeTextFieldItem(
                        modifier = Modifier
                            .height(99.dp)
                            .weight(1f),
                        value = otpCodePart5,
                        onValueChange = {
                            otpCodePart5 = it
                            if (it.isNotEmpty()) focusManager.moveFocus(FocusDirection.Next)
                            else focusManager.moveFocus(FocusDirection.Previous)
                        })
                    Spacer(modifier = Modifier.width(12.dp))
                    OTPCodeTextFieldItem(
                        modifier = Modifier
                            .height(99.dp)
                            .weight(1f),
                        value = otpCodePart6,
                        onValueChange = {
                            otpCodePart6 = it
                            if (it.isEmpty()) focusManager.moveFocus(FocusDirection.Previous)
                        })
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .offset(x = -4.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {

                            }) {
                        Text(
                            modifier = Modifier.padding(4.dp),
                            text = stringResource(R.string.send_again),
                            fontFamily = raleway,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = FormatUtils.timeInSecondsToString(state.time),
                        fontFamily = raleway,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            if (state.showCheckYourEmailDialog) {
                CheckYourEmailDialog(onDismissRequest = { state.showCheckYourEmailDialog = false })
            }

            if (state.contentState.isLoading.value) {
                LoadingDialog(onDismissRequest = {})
            }
        }

        LaunchedEffect(state.success) {
            if (state.success) navHostController.navigate("${Route.NewPasswordScreen.route}/${state.email}")
        }
    }
}

@Preview
@Composable
private fun ForgotPasswordScreenPreview() {
    MatuleMeTheme {
        OTPVerificationScreen(
            navHostController = rememberNavController(), viewModel = hiltViewModel()
        )
    }
}