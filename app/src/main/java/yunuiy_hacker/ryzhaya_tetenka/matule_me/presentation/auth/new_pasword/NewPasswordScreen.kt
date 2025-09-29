package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.new_pasword

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import yunuiy_hacker.ryzhaya_tetenka.matule_me.R
import yunuiy_hacker.ryzhaya_tetenka.matule_me.nav_graph.Route
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.sign_in.SignInEvent
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.sign_up.SignUpEvent
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.common.composable.dialogs.LoadingDialog
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.common.composable.dialogs.MessageDialog
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.common.composable.TextField
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.common.composable.dialogs.InternetIsNotAvailableDialog
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.common.composable.dialogs.ServerIsNotAvailableDialog
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.common.composable.dialogs.SuccessDialog
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.BlockBackgroundColor
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.HintColor
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.MatuleMeTheme
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.raleway

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPasswordScreen(
    navHostController: NavHostController,
    viewModel: NewPasswordViewModel = hiltViewModel<NewPasswordViewModel>()
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
                                    Route.OTPVerificationScreen.route, inclusive = false
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
                    text = stringResource(R.string.password_change),
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
                    text = stringResource(R.string.enter_your_new_password_and_his_confirmation),
                    fontSize = 16.sp,
                    fontFamily = raleway,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(40.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.password,
                    onValueChange = {
                        viewModel.onEvent(NewPasswordEvent.ChangePasswordEvent(it))
                    },
                    label = stringResource(R.string.password),
                    placeholder = "********",
                    trailingIcon = {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .clickable {
                                    viewModel.onEvent(NewPasswordEvent.TogglePasswordVisibilityEvent)
                                }) {
                            Icon(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .padding(6.dp),
                                painter = painterResource(if (state.passwordIsVisible) R.drawable.ic_eye_open else R.drawable.ic_eye_close),
                                tint = HintColor,
                                contentDescription = null,
                            )
                        }
                    },
                    visualTransformation = if (state.passwordIsVisible) VisualTransformation.None else PasswordVisualTransformation(
                        mask = '*'
                    ),
                    keyBoardOptions = KeyboardOptions(
                        keyboardType = if (state.passwordIsVisible) KeyboardType.Text else KeyboardType.Password,
                        imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.height(30.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.passwordConfirmation,
                    onValueChange = {
                        viewModel.onEvent(NewPasswordEvent.ChangePasswordConfirmationEvent(it))
                    },
                    label = stringResource(R.string.password_confirmation),
                    placeholder = "********",
                    trailingIcon = {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .clickable {
                                    viewModel.onEvent(NewPasswordEvent.TogglePasswordConfirmationVisibilityEvent)
                                }) {
                            Icon(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .padding(6.dp),
                                painter = painterResource(if (state.passwordConfirmationIsVisible) R.drawable.ic_eye_open else R.drawable.ic_eye_close),
                                tint = HintColor,
                                contentDescription = null,
                            )
                        }
                    },
                    visualTransformation = if (state.passwordConfirmationIsVisible) VisualTransformation.None else PasswordVisualTransformation(
                        mask = '*'
                    ),
                    keyBoardOptions = KeyboardOptions(
                        keyboardType = if (state.passwordConfirmationIsVisible) KeyboardType.Text else KeyboardType.Password,
                        imeAction = ImeAction.Done
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
                    onClick = { viewModel.onEvent(NewPasswordEvent.OnClickButtonEvent) }) {
                    Text(
                        modifier = Modifier.padding(vertical = 10.dp),
                        text = stringResource(R.string.change),
                        fontFamily = raleway,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }
            }

            if (state.showMessageDialog) {
                MessageDialog(
                    onDismissRequest = { viewModel.onEvent(NewPasswordEvent.HideMessageDialogEvent) },
                    message = state.message
                )
            }

            if (state.showSuccessDialog) {
                SuccessDialog(onDismissRequest = {})
            }

            if (!state.contentState.internetIsAvailable.value) {
                InternetIsNotAvailableDialog(onDismissRequest = {
                    viewModel.onEvent(NewPasswordEvent.HideInternetIsNotAvailableDialogEvent)
                })
            }

            if (!state.contentState.serverIsAvailable.value) {
                ServerIsNotAvailableDialog(onDismissRequest = {
                    viewModel.onEvent(NewPasswordEvent.HideServerIsNotAvailableDialogEvent)
                })
            }

            if (state.contentState.isLoading.value) {
                LoadingDialog(onDismissRequest = {})
            }
        }

        LaunchedEffect(state.success) {
            if (state.success) navHostController.navigate(Route.SignInScreen.route)
        }

        BackHandler {
            navHostController.popBackStack(Route.OTPVerificationScreen.route, inclusive = false)
        }
    }
}

@Preview
@Composable
private fun NewPasswordScreenPreview() {
    MatuleMeTheme {
        NewPasswordScreen(
            navHostController = rememberNavController(), viewModel = hiltViewModel()
        )
    }
}