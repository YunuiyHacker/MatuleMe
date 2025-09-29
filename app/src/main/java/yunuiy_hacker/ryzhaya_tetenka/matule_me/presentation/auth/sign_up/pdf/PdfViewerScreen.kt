package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.sign_up.pdf

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import yunuiy_hacker.ryzhaya_tetenka.matule_me.R
import yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.common.composable.dialogs.LoadingDialog
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.BlockBackgroundColor
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.raleway

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PdfViewerScreen(
    navHostController: NavHostController, viewModel: PdfViewerViewModel = hiltViewModel()
) {

    var scale by remember {
        mutableStateOf(1f)
    }
    var offsetX by remember {
        mutableStateOf(0f)
    }
    var offsetY by remember {
        mutableStateOf(0f)
    }

    LaunchedEffect(Unit) {
        viewModel.onEvent(PdfViewerEvent.LoadDataEvent)
    }

    viewModel.state.let { state ->
        Scaffold(containerColor = MaterialTheme.colorScheme.background, topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier.offset(x = -24.dp),
                            text = stringResource(R.string.privacy_policy),
                            fontFamily = raleway,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            lineHeight = 20.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                navigationIcon = {
                    Row {
                        Spacer(modifier = Modifier.width(20.dp))
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(color = BlockBackgroundColor)
                                .clickable {
                                    navHostController.popBackStack()
                                }) {
                            Icon(
                                modifier = Modifier.padding(10.dp),
                                painter = painterResource(R.drawable.ic_back),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                })
        }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(11.dp))
                if (!state.contentState.isLoading.value) {
                    LazyColumn(
                        modifier = Modifier
                    ) {
                        items(state.pdfPages) { pdfPage ->
                            Image(
                                modifier = Modifier,
                                bitmap = pdfPage.asImageBitmap(),
                                contentDescription = null
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(3.dp)
                            .padding(bottom = 24.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(height = 72.dp, width = 3.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                                    shape = CircleShape
                                )
                        )
                    }
                }
            }
        }

        if (state.contentState.isLoading.value) {
            LoadingDialog(onDismissRequest = {})
        }
    }
}