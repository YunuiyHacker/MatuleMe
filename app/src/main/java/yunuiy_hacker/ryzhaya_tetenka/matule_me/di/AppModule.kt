package yunuiy_hacker.ryzhaya_tetenka.matule_me.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import yunuiy_hacker.ryzhaya_tetenka.matule_me.BuildConfig
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.SupabaseClientBuilder
import io.github.jan.supabase.annotations.SupabaseInternal
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.network.SupabaseHttpClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.serializer.KotlinXSerializer
import yunuiy_hacker.ryzhaya_tetenka.matule_me.data.local.shared_prefs.SharedPrefsHelper
import yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.change_password.ChangePasswordUseCase
import yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.change_password.CheckingOTPCodeAccuracyUseCase
import yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.change_password.ResendOTPCodeUseCase
import yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.common.use_case.CheckingEmailForRegistrationOperator
import yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.common.use_case.CheckingServerAvailableUseCase
import yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.forgot_password.SendRequestForTakeOTPCodeUseCase
import yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.sign_in.SignInUseCase
import yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.sign_up.CreateNewUserOperator
import yunuiy_hacker.ryzhaya_tetenka.matule_me.domain.sign_up.SignUpUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPrefsHelper(@ApplicationContext context: Context): SharedPrefsHelper =
        SharedPrefsHelper(context)

    @OptIn(SupabaseInternal::class)
    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient = createSupabaseClient(
        supabaseUrl = BuildConfig.SUPABASE_URL, supabaseKey = BuildConfig.SUPABASE_KEY
    ) {
        defaultSerializer = KotlinXSerializer()
        install(Auth)
        install(Postgrest)
    }

    @Provides
    @Singleton
    fun provideCheckingServerAvailableUseCase(supabaseClient: SupabaseClient): CheckingServerAvailableUseCase =
        CheckingServerAvailableUseCase(supabaseClient)

    @Provides
    @Singleton
    fun provideCheckingEmailForRegistrationOperator(supabaseClient: SupabaseClient): CheckingEmailForRegistrationOperator =
        CheckingEmailForRegistrationOperator(supabaseClient)

    @Provides
    @Singleton
    fun provideSignInUseCase(supabaseClient: SupabaseClient): SignInUseCase =
        SignInUseCase(supabaseClient)

    @Provides
    @Singleton
    fun provideSignUpUseCase(supabaseClient: SupabaseClient): SignUpUseCase = SignUpUseCase(
        checkingEmailForRegistrationOperator = CheckingEmailForRegistrationOperator(
            supabaseClient
        ), createNewUserOperator = CreateNewUserOperator(supabaseClient)
    )

    @Provides
    @Singleton
    fun provideSendRequestForTakeOTPCodeUseCase(supabaseClient: SupabaseClient): SendRequestForTakeOTPCodeUseCase =
        SendRequestForTakeOTPCodeUseCase(supabaseClient)

    @Provides
    @Singleton
    fun provideResendOTPCodeUseCase(supabaseClient: SupabaseClient): ResendOTPCodeUseCase =
        ResendOTPCodeUseCase(supabaseClient)

    @Provides
    @Singleton
    fun provideChangePasswordUseCase(supabaseClient: SupabaseClient): ChangePasswordUseCase =
        ChangePasswordUseCase(supabaseClient)

    @Provides
    @Singleton
    fun provideCheckingOTPCodeAccuracyUseCase(supabaseClient: SupabaseClient): CheckingOTPCodeAccuracyUseCase =
        CheckingOTPCodeAccuracyUseCase(supabaseClient)
}