package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.sign_up.pdf

import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class PdfViewerViewModel @Inject constructor(@ApplicationContext private val context: Context) :
    ViewModel() {
    val state by mutableStateOf(PdfViewerState())

    fun onEvent(event: PdfViewerEvent) {
        when (event) {
            PdfViewerEvent.LoadDataEvent -> loadData()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun loadData() {
        state.contentState.isLoading.value = true
        GlobalScope.launch(Dispatchers.IO) {
            runBlocking {
                val pdfPages = mutableListOf<Bitmap>()

                val pdfRenderer =
                    PdfRenderer(context.assets.openFd("raw/privacy_policy.pdf").parcelFileDescriptor)
                val pageCount = pdfRenderer.pageCount
                for (i in 0..<pageCount) {
                    val page = pdfRenderer.openPage(i)
                    val width = context.resources.displayMetrics.densityDpi / 72 * page.width
                    val height = context.resources.displayMetrics.densityDpi / 72 * page.height
                    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
                    page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                    pdfPages.add(bitmap)
                    page.close()
                }
                pdfRenderer.close()

                state.pdfPages = pdfPages

                state.contentState.isLoading.value = false
            }
        }
    }
}