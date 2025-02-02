package tech.thdev.compose.web.sample

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.children
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import tech.thdev.compose.web.sample.ui.holder.web.CustomWebChromeClient
import tech.thdev.compose.web.sample.ui.holder.web.CustomWebViewClient
import tech.thdev.compose.web.sample.ui.holder.web.LocalWebOwner
import tech.thdev.compose.web.sample.ui.model.ListItem
import tech.thdev.compose.web.sample.ui.model.NavigationSample
import tech.thdev.compose.web.sample.ui.theme.MyApplicationTheme

/**
 * Step 1.
 * full code
 */
class MainActivityBackup : ComponentActivity() {
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
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
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
                        var listItem by remember { mutableStateOf(ListItem(emptyList())) }
                        Column {
                            LazyColumn(
                                contentPadding = PaddingValues(20.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            ) {
                                items(listItem.items) { item ->
                                    Surface(
                                        shape = MaterialTheme.shapes.small,
                                    ) {
                                        if (item.editMode) {
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(16.dp)
                                            ) {
                                                var changeItem by remember { mutableStateOf(item) }
                                                TextField(
                                                    value = changeItem.text,
                                                    onValueChange = { new ->
                                                        changeItem = changeItem.copy(
                                                            text = new,
                                                        )
                                                    },
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                )

                                                Row {
                                                    Button(
                                                        onClick = {
                                                            listItem = listItem.copy(
                                                                items = listItem.items.map { listItem ->
                                                                    if (listItem.index == item.index) {
                                                                        changeItem.copy(
                                                                            editMode = false,
                                                                        )
                                                                    } else {
                                                                        listItem
                                                                    }
                                                                },
                                                            )
                                                        },
                                                        modifier = Modifier
                                                            .weight(1f)
                                                    ) {
                                                        Text(
                                                            text = "Save",
                                                        )
                                                    }

                                                    Button(
                                                        onClick = {
                                                            listItem = listItem.copy(
                                                                items = listItem.items.toMutableList()
                                                                    .also { newList ->
                                                                        newList.remove(item)
                                                                    },
                                                            )
                                                        },
                                                        modifier = Modifier
                                                            .weight(1f)
                                                            .padding(start = 10.dp)
                                                    ) {
                                                        Text(
                                                            text = "X",
                                                        )
                                                    }
                                                }
                                            }
                                        } else {
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .background(color = Color.Gray.copy(0.3f))
                                            ) {
                                                Row {
                                                    Text(
                                                        text = item.text,
                                                        modifier = Modifier
                                                            .weight(1f)
                                                            .padding(horizontal = 16.dp)
                                                            .padding(top = 16.dp)
                                                    )

                                                    IconButton(
                                                        onClick = {
                                                            listItem = listItem.copy(
                                                                items = listItem.items.toMutableList()
                                                                    .also { newList ->
                                                                        newList.remove(item)
                                                                    },
                                                            )
                                                        },
                                                    ) {
                                                        Icon(
                                                            painter = painterResource(id = R.drawable.baseline_close_24),
                                                            contentDescription = "remove",
                                                        )
                                                    }
                                                }

                                                Button(
                                                    onClick = {
                                                        listItem = listItem.copy(
                                                            items = listItem.items.map { listItem ->
                                                                if (listItem == item) {
                                                                    listItem.copy(
                                                                        editMode = true,
                                                                    )
                                                                } else {
                                                                    listItem
                                                                }
                                                            },
                                                        )
                                                    },
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(horizontal = 16.dp)
                                                        .padding(top = 10.dp, bottom = 16.dp)
                                                ) {
                                                    Text(
                                                        text = "edit",
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            Button(
                                onClick = {
                                    listItem = listItem.copy(
                                        items = listItem.items.toMutableList().also { list ->
                                            list.add(
                                                ListItem.Item(
                                                    index = list.size,
                                                    text = "",
                                                    editMode = true,
                                                )
                                            )
                                        },
                                    )
                                },
                                modifier = Modifier
                                    .padding(20.dp)
                            ) {
                                Text(
                                    text = "New",
                                )
                            }
                        }
                    }

                    composable(route = NavigationSample.Trigger.WEB.name) {
                        val chromeClient = CustomWebChromeClient()
                        val client = CustomWebViewClient()

                        val webView = LocalWebOwner.current

                        var url by remember { mutableStateOf("https://thdev.tech/") }

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            TextField(
                                value = url,
                                onValueChange = { new ->
                                    url = new
                                },
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                                keyboardActions = KeyboardActions(
                                    onSearch = {
                                        webView?.loadUrl(url)
                                    },
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                                    .padding(bottom = 8.dp)
                            )

                            BoxWithConstraints(
                                modifier = Modifier
                                    .weight(1f)
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

                                LaunchedEffect(key1 = Unit) {
                                    webView?.loadUrl(url)
                                }

                                BackHandler {
                                    if (webView?.canGoBack() == true) {
                                        webView.goBack()
                                    }
                                }

                                AndroidView(
                                    factory = {
                                        val parentLayout = FrameLayout(context).apply {
                                            val web = webView ?: WebView(context)

                                            web.apply {
                                                this.layoutParams = layoutParams
                                                settings.run {
                                                    javaScriptEnabled = true
                                                    defaultTextEncodingName = "UTF-8"
                                                    loadWithOverviewMode = true
                                                    useWideViewPort = true
                                                    setSupportZoom(true)

                                                    mixedContentMode =
                                                        WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE
                                                    setNetworkAvailable(true)
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
                                        (parentFrame.children.first() as? WebView)?.let { web ->
                                            parentFrame.removeView(web)
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