package tech.thdev.compose.web.sample

import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.children
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import tech.thdev.compose.web.sample.ui.holder.web.LocalWebOwner
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

@Composable
internal fun MainScreen(
    list: List<NavigationSample>,
    navController: NavHostController = rememberNavController(),
) {
    val listItem = remember {
        mutableStateListOf<ListItem>()
    }
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
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
            val context = LocalContext.current
            CompositionLocalProvider(
                LocalWebOwner provides WebView(context)
            ) {
                NavHost(
                    navController = navController,
                    startDestination = NavigationSample.Trigger.HOME.name,
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None },
                ) {
                    composable(route = NavigationSample.Trigger.HOME.name) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            items(listItem) { item ->
                                Row {
                                    Text(
                                        text = item.text,
                                    )
                                }
                            }
                        }
                    }

                    composable(route = NavigationSample.Trigger.WEB.name) {
                        val chromeClient = object : WebChromeClient() {}
                        val client = object : WebViewClient() {}

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            BoxWithConstraints(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                // WebView changes it's layout strategy based on
                                // it's layoutParams. We convert from Compose Modifier to
                                // layout params here.
                                val width = if (constraints.hasFixedWidth) {
                                    ViewGroup.LayoutParams.MATCH_PARENT
                                } else {
                                    ViewGroup.LayoutParams.WRAP_CONTENT
                                }
                                val height = if (constraints.hasFixedHeight) {
                                    ViewGroup.LayoutParams.MATCH_PARENT
                                } else {
                                    ViewGroup.LayoutParams.WRAP_CONTENT
                                }

                                val layoutParams = FrameLayout.LayoutParams(
                                    width,
                                    height
                                )

                                val webView = LocalWebOwner.current

                                LaunchedEffect(key1 = Unit) {
                                    webView?.loadUrl("https://thdev.tech/")
                                }

                                AndroidView(
                                    factory = {
                                        val parentLayout = FrameLayout(context).apply {
                                            val web = webView ?: WebView(context).apply {
                                                this.layoutParams = layoutParams
                                                settings.run {
                                                    javaScriptEnabled = true
                                                    defaultTextEncodingName = "UTF-8"

                                                    cacheMode = WebSettings.LOAD_DEFAULT

                                                    setSupportMultipleWindows(true)
                                                }

                                                webChromeClient = chromeClient
                                                webViewClient = client
                                            }

                                            addView(web)
                                        }
                                        parentLayout
                                    },
                                    onRelease = { parentFrame ->
                                        (parentFrame.children.first() as? WebView)?.let {
                                            parentFrame.removeView(it)
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}