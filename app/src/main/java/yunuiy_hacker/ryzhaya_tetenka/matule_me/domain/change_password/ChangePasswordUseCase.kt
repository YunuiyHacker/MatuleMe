package yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.change_password

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import yunuiy_hacker.ryzhaya_tetenka.matule_me.data.common.model.User

class ChangePasswordUseCase(private val supabaseClient: SupabaseClient) {
    suspend fun execute(email: String, newPassword: String): User? {
        val authUser = supabaseClient.auth.updateUser {
            this.email = email
            this.password = newPassword
        }

        return supabaseClient.from("users").update({
            User::password setTo newPassword
        }) {
            filter {
                User::email eq email
            }
            select()
        }.decodeSingleOrNull<User>()
    }
}