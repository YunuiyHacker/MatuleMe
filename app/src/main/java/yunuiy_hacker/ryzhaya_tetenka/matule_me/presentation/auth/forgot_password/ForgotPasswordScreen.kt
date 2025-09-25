package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.forgot_password

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.common.composable.TextField
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.forgot_password.composable.CheckYourEmailDialog
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.BlockBackgroundColor
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.MatuleMeTheme
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.raleway

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    navHostController: NavHostController,
    viewModel: ForgotPasswordViewModel = hiltViewModel<ForgotPasswordViewModel>()
) {
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
                                    Route.SignInScreen.route, inclusive = false
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
                    text = stringResource(R.string.forgot_password),
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
                    text = stringResource(R.string.enter_your_email_account_to_reset_your_password),
                    fontSize = 16.sp,
                    fontFamily = raleway,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(40.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(), value = state.email, onValueChange = {
                        viewModel.onEvent(ForgotPasswordEvent.ChangeEmailEvent(it.lowercase()))
                    }, placeholder = "xyz@gmail.com", keyBoardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email, imeAction = ImeAction.Done
                    )
                )
                Spacer(modifier = Modifier.height(40.dp))
                Button(
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = BlockBackgroundColor
                    ),
                    onClick = { viewModel.onEvent(ForgotPasswordEvent.OnClickButtonEvent) }) {
                    Text(
                        modifier = Modifier.padding(vertical = 10.dp),
                        text = stringResource(R.string.send),
                        fontFamily = raleway,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }
            }

            if (state.showCheckYourEmailDialog) {
                CheckYourEmailDialog(onDismissRequest = { viewModel.onEvent(ForgotPasswordEvent.HideCheckYourEmailDialogEvent) })
            }

            if (state.contentState.isLoading.value) {
                LoadingDialog(onDismissRequest = {})
            }
        }

        LaunchedEffect(state.success) {
            if (state.success == true) navHostController.navigate("${Route.OTPVerificationScreen.route}/${state.email}")
        }
    }
}

@Preview
@Composable
private fun ForgotPasswordScreenPreview() {
    MatuleMeTheme {
        ForgotPasswordScreen(
            navHostController = rememberNavController(), viewModel = hiltViewModel()
        )
    }
}