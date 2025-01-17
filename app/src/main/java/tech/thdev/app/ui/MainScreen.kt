package tech.thdev.app.ui

import android.webkit.WebView
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tech.thdev.app.ui.holder.other.OtherSampleScreen
import tech.thdev.app.ui.holder.web.LocalWebOwner
import tech.thdev.app.ui.holder.web.WebSampleScreen

object SamplA {

    const val A = "a"
}

@Composable
internal fun MainScreen(
    navController: NavHostController = rememberNavController(),
) {
    val context = LocalContext.current
    CompositionLocalProvider(
        LocalWebOwner provides WebView(context)
    ) {
        NavHost(
            navController = navController,
            startDestination = "WEB",
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
        ) {
            composable(route = "WEB") {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    WebSampleScreen()

                    Button(
                        onClick = {
                            navController.navigate("OTHER") {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        },
                    ) {
                        Text(text = "Next button")
                    }
                }
            }
            composable(route = "OTHER") {
                OtherSampleScreen()
            }
        }
    }
}
