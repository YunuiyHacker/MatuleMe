package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.forgot_password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.common.state.ContentState

class ForgotPasswordState {
    var email by mutableStateOf("")

    var emailIsValid by mutableStateOf(false)

    var showCheckYourEmailDialog by mutableStateOf(false)

    var message by mutableStateOf("")
    var showMessageDialog by mutableStateOf(false)

    var contentState by mutableStateOf(ContentState())

    var success by mutableStateOf(false)
}