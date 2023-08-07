package com.ekasoftware.english.navigations


/*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ekasoftware.english.assets.Screen
import com.ekasoftware.english.ui.theme.UiColor
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius


var selectedIndex = 0

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationPage(navController: NavHostController) {

    val navigationBarItems = remember { NavigationBarItems.values() }

    Scaffold(
        modifier = Modifier.padding(16.dp),
        bottomBar = {
            AnimatedNavigationBar(
                modifier = Modifier.height(64.dp),
                selectedIndex = selectedIndex,
                cornerRadius = shapeCornerRadius(cornerRadius = 34.dp),
                ballAnimation = Parabolic(tween(300)),
                indentAnimation = Height(tween(300)),
                barColor = UiColor,
                ballColor = UiColor,
            ) {
                navigationBarItems.forEachIndexed { itemIndex, item ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .noRippleClickable(navController, itemIndex) {

                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = "",
                            modifier = Modifier,
                            tint = if (selectedIndex == itemIndex) Color.White else Color.LightGray,
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        val paddingValues = Modifier.padding(innerPadding)
    }
}

var clickRoute = listOf<String>(
    Screen.Home.route,
    Screen.Settings.route
)

enum class NavigationBarItems(val icon : ImageVector) {
    Home(Icons.Default.Home),
    Settings(Icons.Default.Settings)
}

fun Modifier.noRippleClickable(
    navController: NavHostController,
    index: Int,
    onClick: () -> Unit
): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick
        navController.navigate(clickRoute[index])
        selectedIndex = index
    }
}



@Preview
@Composable
fun Preview() {
    val navController : NavHostController
    navController = rememberNavController()
    NavigationPage(navController)
}
*/