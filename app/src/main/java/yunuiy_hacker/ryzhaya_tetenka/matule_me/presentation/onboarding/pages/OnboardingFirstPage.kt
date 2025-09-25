package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.onboarding.pages

import android.R.attr.y
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.ktor.util.toUpperCasePreservingASCIIRules
import yunuiy_hacker.ryzhaya_tetenka.matule_me.R
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.raleway
import java.util.Locale

@Composable
fun OnboardingFirstPage(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(29.dp))
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 80.dp)
            ) {
                Image(
                    modifier = Modifier.size(width = 27.dp, height = 30.dp).offset(x = 14.dp, y = 6.dp),
                    painter = painterResource(R.drawable.onboarding_1_highlight_1),
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.welcome).uppercase(),
                    color = Color.White,
                    fontFamily = raleway,
                    fontWeight = FontWeight.Black,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 37.sp
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                modifier = Modifier
                    .size(width = 100.dp, height = 12.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(R.drawable.onboarding_1_highlight_2),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(24.dp))
            Image(
                modifier = Modifier.fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 30.dp),
                painter = painterResource(R.drawable.onboarding_1_highlight_group),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            painter = painterResource(R.drawable.onboarding_1_img),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}