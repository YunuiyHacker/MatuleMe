package yunuiy_hacker.ryzhaya_tetenka.matule_me.data.common.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int? = null,
    val name: String? = null,
    val surname: String? = null,
    val email: String? = null,
    val password: String? = null,
    val address: String? = null,
    val phone: String? = null,
    val img_path: String? = null,
    val barcode: String? = null
)
