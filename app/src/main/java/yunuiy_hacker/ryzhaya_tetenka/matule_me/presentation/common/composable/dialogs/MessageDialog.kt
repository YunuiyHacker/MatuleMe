package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.common.composable.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.raleway

@Composable
fun MessageDialog(modifier: Modifier = Modifier, onDismissRequest: () -> Unit, message: String) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Box(
            modifier = modifier.background(
                color = MaterialTheme.colorScheme.background, shape = RoundedCornerShape(10.dp)
            )
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = message,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = raleway
            )
        }
    }
}