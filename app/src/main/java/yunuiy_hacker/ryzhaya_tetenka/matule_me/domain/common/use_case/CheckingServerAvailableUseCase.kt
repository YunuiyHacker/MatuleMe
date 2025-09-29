package yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.common.use_case

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import yunuiy_hacker.ryzhaya_tetenka.matule_me.data.common.model.User

class CheckingServerAvailableUseCase(private val supabaseClient: SupabaseClient) {
    suspend fun execute(): Boolean {
        try {
            supabaseClient.from("users").select().decodeSingleOrNull<User>()

            return true
        } catch (e: Exception) {
            return false
        }
    }
}