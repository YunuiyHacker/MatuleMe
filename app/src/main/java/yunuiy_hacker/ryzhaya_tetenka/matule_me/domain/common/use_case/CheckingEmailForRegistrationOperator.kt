package yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.common.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import yunuiy_hacker.ryzhaya_tetenka.matule_me.data.common.model.User

class CheckingEmailForRegistrationOperator(private val supabaseClient: SupabaseClient) {
    suspend operator fun invoke(email: String): Boolean {
        return supabaseClient.from("users").select {
            filter { User::email eq email }
        }.decodeSingleOrNull<User>() != null
    }
}