package yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.sign_in

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import yunuiy_hacker.ryzhaya_tetenka.matule_me.data.common.model.User
import yunuiy_hacker.ryzhaya_tetenka.matule_me.utils.CryptoUtils

class SignInUseCase(private val supabaseClient: SupabaseClient) {
    suspend fun execute(user: User): User? {
        return supabaseClient.from("users").select {
            filter {
                User::email eq user.email
                and { User::password eq CryptoUtils.toSHA256(user.password!!) }
            }
        }.decodeSingleOrNull<User>()
    }
}