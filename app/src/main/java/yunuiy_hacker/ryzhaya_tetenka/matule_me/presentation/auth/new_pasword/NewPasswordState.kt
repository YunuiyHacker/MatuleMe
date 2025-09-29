package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.new_pasword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.common.state.ContentState

class NewPasswordState {
    var email by mutableStateOf("alfirnurkaev7@gmail.com")
    var password by mutableStateOf("")
    var passwordConfirmation by mutableStateOf("")

    var passwordIsVisible by mutableStateOf(false)
    var passwordConfirmationIsVisible by mutableStateOf(false)

    var passwordIsValid by mutableStateOf(false)
    var passwordConfirmationIsValid by mutableStateOf(false)

    var message by mutableStateOf("")
    var showMessageDialog by mutableStateOf(false)
    var showSuccessDialog by mutableStateOf(false)

    var contentState by mutableStateOf(ContentState())

    var success by mutableStateOf(false)
}