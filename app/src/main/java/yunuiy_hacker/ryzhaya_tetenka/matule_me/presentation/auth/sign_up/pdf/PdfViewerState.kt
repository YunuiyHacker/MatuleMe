package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.sign_up.pdf

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.common.state.ContentState

class PdfViewerState {
    var pdfPages: List<Bitmap> = mutableListOf()
    var pdfFileUrl by mutableStateOf("")

    val contentState by mutableStateOf(ContentState())
}