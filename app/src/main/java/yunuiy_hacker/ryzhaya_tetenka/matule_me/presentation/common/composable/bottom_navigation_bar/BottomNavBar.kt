package yunuiy_hacker.ryzhaya_tetenka.matule_me.presentation.common.composable.bottom_navigation_bar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import yunuiy_hacker.ryzhaya_tetenka.matule_me.R
import yunuiy_hacker.ryzhaya_tetenka.matule_me.nav_graph.Route
import yunuiy_hacker.ryzhaya_tetenka.matule_me.ui.theme.SubTextDarkColor

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier, selectedRoute: String, onChangeRoute: (String) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.25f),
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.125f),
                            Color.Transparent,
                            Color.Transparent,
                        )
                    )
                )
                .align(Alignment.TopCenter)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.primary)
                    .align(Alignment.Center)
                    .clickable {

                    }) {
                Icon(
                    modifier = Modifier.padding(16.dp),
                    painter = painterResource(R.drawable.ic_bag),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(R.drawable.bottom_navigation_bar_background),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        NavigationBar(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 20.dp),
            containerColor = Color.Unspecified
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            onChangeRoute(Route.HomeScreen.route)
                        }) {
                    Icon(
                        modifier = Modifier.padding(8.dp),
                        painter = painterResource(R.drawable.ic_home),
                        contentDescription = null,
                        tint = if (selectedRoute == Route.HomeScreen.route) MaterialTheme.colorScheme.primary else SubTextDarkColor
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            onChangeRoute(Route.FavoriteScreen.route)
                        }) {
                    Icon(
                        modifier = Modifier.padding(8.dp),
                        painter = painterResource(R.drawable.ic_favorite),
                        contentDescription = null,
                        tint = if (selectedRoute == Route.FavoriteScreen.route) MaterialTheme.colorScheme.primary else SubTextDarkColor
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable(interactionSource = null, indication = null) {

                        }) {

                }
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable(interactionSource = null, indication = null) {

                        }) {

                }
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            onChangeRoute(Route.NotificationScreen.route)
                        }) {
                    Icon(
                        modifier = Modifier.padding(8.dp),
                        painter = painterResource(R.drawable.ic_notification),
                        contentDescription = null,
                        tint = if (selectedRoute == Route.NotificationScreen.route) MaterialTheme.colorScheme.primary else SubTextDarkColor
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            onChangeRoute(Route.ProfileScreen.route)
                        }) {
                    Icon(
                        modifier = Modifier.padding(8.dp),
                        painter = painterResource(R.drawable.ic_profile),
                        contentDescription = null,
                        tint = if (selectedRoute == Route.ProfileScreen.route) MaterialTheme.colorScheme.primary else SubTextDarkColor
                    )
                }
            }
        }
    }
}