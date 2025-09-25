package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.sign_up.pdf

sealed class PdfViewerEvent {
    data object LoadDataEvent : PdfViewerEvent()
}