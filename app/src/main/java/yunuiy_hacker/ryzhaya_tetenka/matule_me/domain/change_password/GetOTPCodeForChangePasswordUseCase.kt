package yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.change_password

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth

class GetOTPCodeForChangePasswordUseCase(private val supabaseClient: SupabaseClient) {
    suspend fun execute(email: String, token: String): String {
        supabaseClient.auth.verifyEmailOtp(type = OtpType.Email.RECOVERY, email = email, token = token)
    }
}