package tech.thdev.architecture.app.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import tech.thdev.architecture.app.common.alert.AlertDialogScreen
import tech.thdev.architecture.app.common.snackbar.SnackbarScreen
import tech.thdev.architecture.app.event.FlowComposeInteractionTrigger
import tech.thdev.architecture.app.event.LocalComposeEventTriggerOwner
import tech.thdev.architecture.app.event.MainEffect
import tech.thdev.architecture.app.ui.theme.MyApplicationTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var flowComposeInteractionTrigger: FlowComposeInteractionTrigger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mainViewModel.flowLoad()

        setContent {
            MyApplicationTheme {
                CompositionLocalProvider(LocalComposeEventTriggerOwner provides flowComposeInteractionTrigger) {
                    val snackbarHostState = remember { SnackbarHostState() }

                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text("Architecture sample")
                                }
                            )
                        },
                        snackbarHost = {
                            SnackbarHost(hostState = snackbarHostState)
                        },
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it)
                                .padding(horizontal = 10.dp)
                        ) {
                            val triggerEvent = LocalComposeEventTriggerOwner.current
                            Button(
                                onClick = {
                                    triggerEvent.action(MainEffect.ShowAlert)
                                }
                            ) {
                                Text(
                                    text = "Show alert only",
                                )
                            }

                            Button(
                                onClick = {
                                    triggerEvent.action(MainEffect.ShowAlertAndSnackbar)
                                }
                            ) {
                                Text(
                                    text = "Show alert and Snackbar",
                                )
                            }

                            val mainUiState by mainViewModel.mainUiState.collectAsStateWithLifecycle()
                            Text(
                                text = mainUiState.buttonEndEvent,
                                modifier = Modifier
                                    .padding(top = 10.dp)
                            )
                        }
                    }

                    AlertDialogScreen()
                    SnackbarScreen(
                        snackbarHostState = snackbarHostState,
                    )
                }
            }
        }
    }
}
