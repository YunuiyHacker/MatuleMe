package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.common.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class ContentState(
    val isLoading: MutableState<Boolean> = mutableStateOf(false),
    val internetIsAvailable: MutableState<Boolean> = mutableStateOf(true),
    val serverIsAvailable: MutableState<Boolean> = mutableStateOf(true)
)