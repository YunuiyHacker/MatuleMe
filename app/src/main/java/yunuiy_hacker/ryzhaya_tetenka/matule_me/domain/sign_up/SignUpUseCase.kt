package yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.sign_up

import yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.common.use_case.CheckingEmailForRegistrationOperator

data class SignUpUseCase(
    val checkingEmailForRegistrationOperator: CheckingEmailForRegistrationOperator,
    val createNewUserOperator: CreateNewUserOperator
)