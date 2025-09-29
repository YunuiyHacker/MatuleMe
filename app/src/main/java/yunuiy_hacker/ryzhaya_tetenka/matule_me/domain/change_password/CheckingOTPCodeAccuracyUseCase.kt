package yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.change_password

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.exception.AuthRestException
import io.github.jan.supabase.exceptions.RestException

class CheckingOTPCodeAccuracyUseCase(private val supabaseClient: SupabaseClient) {
    suspend fun execute(email: String, token: String): Result<Boolean> {
        try {
            supabaseClient.auth.verifyEmailOtp(
                type = OtpType.Email.RECOVERY, email = email, token = token
            )
            return Result.success(true)
        } catch (e: RestException) {
            return Result.success(false)
        } catch (e: AuthRestException) {
            return Result.failure(e)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}