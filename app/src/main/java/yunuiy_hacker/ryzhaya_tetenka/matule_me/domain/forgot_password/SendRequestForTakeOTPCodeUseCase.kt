package yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.forgot_password

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth

class SendRequestForTakeOTPCodeUseCase(private val supabaseClient: SupabaseClient) {
    suspend fun execute(email: String) {
        supabaseClient.auth.resetPasswordForEmail(email = email)
    }
}