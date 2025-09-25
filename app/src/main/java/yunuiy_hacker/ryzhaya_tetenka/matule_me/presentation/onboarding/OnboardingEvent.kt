package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.onboarding

sealed class OnboardingEvent {
    data object OnboardingPassedChangeEvent : OnboardingEvent()
}