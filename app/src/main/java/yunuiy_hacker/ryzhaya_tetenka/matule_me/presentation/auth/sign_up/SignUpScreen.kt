package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.sign_up

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import yunuiy_hacker.ryzhaya_tetenka.matule_me.R
import yunuiy_hacker.ryzhaya_tetenka.matule_me.nav_graph.Route
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.common.composable.LoadingDialog
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.common.composable.MessageDialog
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.common.composable.TextField
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.BlockBackgroundColor
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.HintColor
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.MatuleMeTheme
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.raleway

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navHostController: NavHostController, viewModel: SignUpViewModel = hiltViewModel<SignUpViewModel>()
) {
    val interactionSource = remember { MutableInteractionSource() }

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
                                navHostController.popBackStack()
                            }) {
                        Icon(
                            modifier = Modifier.padding(10.dp),
                            painter = painterResource(R.drawable.ic_back),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                })
        }, bottomBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.already_have_account),
                        color = HintColor,
                        fontFamily = raleway,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.width(1.dp))
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                navHostController.navigate(Route.SignInScreen.route)
                            }) {
                        Text(
                            modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp),
                            text = stringResource(R.string.log_in),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontFamily = raleway,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(47.dp))
            }
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
                    text = stringResource(R.string.register_account),
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
                    text = stringResource(R.string.fill_your_details_or_continue_with_social_media),
                    fontSize = 16.sp,
                    fontFamily = raleway,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(30.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.name,
                    onValueChange = {
                        viewModel.onEvent(SignUpEvent.ChangeNameEvent(it))
                    },
                    label = stringResource(R.string.name),
                    placeholder = "xxxxxxxx",
                    keyBoardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.email,
                    onValueChange = {
                        viewModel.onEvent(SignUpEvent.ChangeEmailEvent(it.lowercase()))
                    },
                    label = stringResource(R.string.email_address),
                    placeholder = "xyz@gmail.com",
                    keyBoardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.password,
                    onValueChange = {
                        viewModel.onEvent(SignUpEvent.ChangePasswordEvent(it))
                    },
                    label = stringResource(R.string.password),
                    placeholder = "********",
                    trailingIcon = {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .clickable {
                                    viewModel.onEvent(SignUpEvent.TogglePasswordVisibilityEvent)
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
                        imeAction = ImeAction.Done
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = BlockBackgroundColor, shape = RoundedCornerShape(6.dp)
                            )
                            .padding(7.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .clickable(interactionSource = interactionSource, indication = null) {
                                viewModel.onEvent(SignUpEvent.TogglePrivacyPolicyConfirmEvent)
                            }) {
                        Icon(
                            modifier = Modifier.size(12.dp),
                            painter = painterResource(R.drawable.ic_policy_check),
                            contentDescription = null,
                            tint = if (state.privacyPolicyIsConfirmed) MaterialTheme.colorScheme.primary else HintColor
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        modifier = Modifier.clickable(
                            interactionSource = interactionSource, indication = null
                        ) {
                            navHostController.navigate(Route.PdfViewerScreen.route)
                        },
                        text = stringResource(R.string.i_agree_to_the_processing_of_personal_data),
                        fontFamily = raleway,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = HintColor,
                        textDecoration = TextDecoration.Underline
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = BlockBackgroundColor
                    ),
                    onClick = { viewModel.onEvent(SignUpEvent.OnClickButtonEvent) }) {
                    Text(
                        modifier = Modifier.padding(vertical = 10.dp),
                        text = stringResource(R.string.sign_up),
                        fontFamily = raleway,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        if (state.showMessageDialog) {
            MessageDialog(onDismissRequest = {
                viewModel.onEvent(SignUpEvent.HideMessageDialogEvent)
            }, message = state.message)
        }

        if (state.contentState.isLoading.value) {
            LoadingDialog(onDismissRequest = {})
        }

        LaunchedEffect(state.success) {
            if (state.success) {
                navHostController.navigate(Route.HomeScreen.route)
            }
        }
    }
}

@Preview
@Composable
private fun SignInScreenPreview() {
    MatuleMeTheme {
        SignUpScreen(navHostController = rememberNavController(), viewModel = hiltViewModel())
    }
}