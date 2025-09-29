package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.common.state.ContentState

class SignInState {
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var passwordIsVisible by mutableStateOf(false)

    var emailIsValid by mutableStateOf(false)
    var passwordIsValid by mutableStateOf(false)

    var message by mutableStateOf("")
    var showMessageDialog by mutableStateOf(false)
    var showSuccessDialog by mutableStateOf(false)

    var contentState by mutableStateOf(ContentState())

    var success by mutableStateOf(false)
}