package yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.common.model

data class User(
    val id: Int = 0,
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val password: String = "",
    val address: String = "",
    val phone: String = "",
    val img_path: String = "",
    val barcode: String = ""
)
