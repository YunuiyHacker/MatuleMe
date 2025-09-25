package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.auth.otp_verification.composable

import android.R.attr.fontFamily
import android.R.attr.fontWeight
import android.R.attr.lineHeight
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.BlockBackgroundColor
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.Red
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.poppins

@Composable
fun OTPCodeTextFieldItem(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    interactionSource: MutableInteractionSource? = null,
    isError: Boolean = false
) {

    val interactionSource = remember {
        interactionSource ?: MutableInteractionSource()
    }

    val cornerRadius = 12.dp

    Box(
        modifier = modifier
            .background(
                color = BlockBackgroundColor, shape = RoundedCornerShape(cornerRadius)
            )
            .clip(RoundedCornerShape(cornerRadius))
            .border(
                width = 1.dp,
                color = if (isError) Red else if (interactionSource.collectIsFocusedAsState().value) MaterialTheme.colorScheme.primary else Color.Transparent,
                shape = RoundedCornerShape(cornerRadius)
            ),
    ) {
        OutlinedTextField(
            modifier = Modifier.align(Alignment.Center), value = value, onValueChange = {
                if (it.isNotEmpty()) {
                    val it = it.take(1)
                    if (it.isNotEmpty() && it.toCharArray().first().isDigit()) onValueChange(it)
                } else onValueChange("")
            }, colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                errorBorderColor = Color.Transparent,
                focusedContainerColor = BlockBackgroundColor,
                unfocusedContainerColor = BlockBackgroundColor,
                errorContainerColor = BlockBackgroundColor
            ), textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center
            ), shape = RoundedCornerShape(12.dp), interactionSource = interactionSource
        )
    }
}