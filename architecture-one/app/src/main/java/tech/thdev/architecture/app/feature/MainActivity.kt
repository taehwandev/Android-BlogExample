package tech.thdev.architecture.app.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import tech.thdev.architecture.app.common.alert.compose.AlertDialogScreen
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
                    AlertDialogScreen()

                    Scaffold {
                        Box(
                            modifier = Modifier
                                .padding(it)
                        ) {
                            val triggerEvent = LocalComposeEventTriggerOwner.current
                            Button(
                                onClick = {
                                    triggerEvent.action(MainEffect.ShowAlert)
                                }
                            ) {
                                Text(
                                    text = "Show alert",
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
