package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.main.profile

sealed class ProfileEvent {
    data object LoadDataEvent : ProfileEvent()
}