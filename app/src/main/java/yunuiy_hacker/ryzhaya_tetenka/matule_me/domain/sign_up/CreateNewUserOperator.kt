package yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.sign_up

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import yunuiy_hacker.ryzhaya_tetenka.matule_me.data.common.model.User
import yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.common.mappers.toDomain
import yunuiy_hacker.ryzhaya_tetenka.matule_me.utils.CryptoUtils

class CreateNewUserOperator(private val supabaseClient: SupabaseClient) {
    suspend operator fun invoke(user: User): yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.common.model.User? {
        supabaseClient.auth.signUpWith(Email) {
            email = user.email!!
            password = CryptoUtils.toSHA256(user.password!!)
        }
        return supabaseClient.from("users")
            .insert(user.copy(password = CryptoUtils.toSHA256(user.password!!))) {
                select()
            }.decodeSingleOrNull<User>()?.toDomain()
    }
}