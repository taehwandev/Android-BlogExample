package tech.thdev.compose.web.sample

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import tech.thdev.compose.web.sample.ui.holder.home.HomeScreenThree
import tech.thdev.compose.web.sample.ui.holder.web.LocalWebOwner
import tech.thdev.compose.web.sample.ui.holder.web.WebScreen
import tech.thdev.compose.web.sample.ui.model.ListItem
import tech.thdev.compose.web.sample.ui.model.NavigationSample
import tech.thdev.compose.web.sample.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainScreen(
                    list = listOf(
                        NavigationSample(
                            title = "HOME",
                            icon = R.drawable.baseline_home_24,
                            trigger = NavigationSample.Trigger.HOME,
                        ),
                        NavigationSample(
                            title = "WEB",
                            icon = R.drawable.baseline_web_24,
                            trigger = NavigationSample.Trigger.WEB,
                        ),
                    ),
                )
            }
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreen(
    list: List<NavigationSample>,
    navController: NavHostController = rememberNavController(),
) {
    val context = LocalContext.current
    var listItem by remember { mutableStateOf(ListItem(emptyList())) }
    SideEffect {
        Log.i("TEMP", "side effect - before compositionLocalProvider")
        // screen event.
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    CompositionLocalProvider(
        LocalWebOwner provides WebView(context)
    ) {
        SideEffect {
            Log.i("TEMP", "side effect - after compositionLocalProvider")
        }

        val currentDestination = navBackStackEntry?.destination
        SideEffect {
            Log.i("TEMP", "side effect - navBackStackEntry")
        }
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = currentDestination?.route ?: "")
                    },
                )
            },
            bottomBar = {
                NavigationBar {
                    list.forEach { screen ->
                        NavigationBarItem(
                            label = {
                                Text(
                                    text = screen.title,
                                )
                            },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.trigger.name } == true,
                            icon = {
                                Icon(
                                    painter = painterResource(id = screen.icon),
                                    contentDescription = screen.title
                                )
                            },
                            onClick = {
                                navController.navigate(screen.trigger.name) {
                                    // Pop up to the start destination of the graph to
                                    // avoid building up a large stack of destinations
                                    // on the back stack as users select items
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    // Avoid multiple copies of the same destination when
                                    // reselecting the same item
                                    launchSingleTop = true
                                    // Restore state when reselecting a previously selected item
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .padding(it)
            ) {
                NavHost(
                    navController = navController,
                    startDestination = NavigationSample.Trigger.HOME.name,
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None },
                ) {
                    composable(route = NavigationSample.Trigger.HOME.name) {
                        HomeScreenThree(
                            listItem = listItem,
                            onEvent = {
                                listItem = it
                            },
                        )
                    }

                    composable(route = NavigationSample.Trigger.WEB.name) {
                        WebScreen()
                    }
                }
            }
        }
    }
}
