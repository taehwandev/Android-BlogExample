package tech.thdev.app.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import tech.thdev.app.R

var count = 0

@Immutable
data class Sample(
    val a: Int = 0,
)

val stateFlow = MutableStateFlow<Boolean>(false)

val LocalOn = compositionLocalOf {
    val arrayList = ArrayList<Int>()
    arrayList.toList()
    val mutableList = mutableListOf<Int>()
    mutableList.toList()
    val list = listOf<Int>()
    Sample()
}

@Composable
fun SampleScreen() {
    val next = stateFlow.collectAsState()
    SideEffect {
        Log.d("TEMP", "before ${next.value}")
    }
    val nextValue = next.value.toString()
    CompositionLocalProvider(
        LocalOn provides Sample(count++)
    ) {
    Text("next $nextValue")
        SideEffect {
            Log.d("TEMP", "after ${next.value}")
        }
        Scaffold {
            Box(
                modifier = Modifier
                    .padding(it)
            ) {
                var show by remember {
                    mutableStateOf(false)
                }
                var typed by remember {
                    mutableStateOf("")
                }

                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .clickable(
                            onClick = {
                                show = show.not()
                                stateFlow.value = stateFlow.value.not()
//                                stateFlow.value = true
                            },
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "home",
                        colorFilter = ColorFilter.tint(Color.Blue),
                        modifier = Modifier
                            .size(100.dp)
                    )

//                    AnimatedVisibility(
//                        visible = show,
//                    ) {
                        Column {
                            Text(
                                text = LocalOn.current.a.toString(),
                                style = MaterialTheme.typography.headlineLarge,
                                modifier = Modifier
                                    .padding(20.dp)
                            )

                            TextField(
                                value = typed,
                                onValueChange = { newTyped ->
                                    typed = newTyped
                                },
                            )
//                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSampleScreen() {
    SampleScreen()
}