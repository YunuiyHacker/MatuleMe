package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.main.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {
    val state by mutableStateOf(ProfileState())

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.LoadDataEvent -> loadData()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun loadData() {
        state.contentState.isLoading.value = true

        GlobalScope.launch(
            Dispatchers.IO
        ) {
            runBlocking {
                try {
                    state.contentState.isLoading.value = false
                } catch (e: Exception) {

                    state.contentState.isLoading.value = false
                }
            }
        }
    }
}