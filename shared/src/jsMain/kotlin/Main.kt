import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.otuskmp.StopwatchViewModel
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable

@Composable
fun <T> StateFlow<T>.collectAsState(): State<T> {
    val state = remember(this) { mutableStateOf(this.value) }
    LaunchedEffect(this) {
        this@collectAsState.collect { state.value = it }
    }
    return state
}

fun main() {
    renderComposable(rootElementId = "root") {
        val viewModel = remember { StopwatchViewModel() }
        val uiState by viewModel.uiState.collectAsState()

        DisposableEffect(Unit) {
            onDispose { viewModel.onDestroy() }
        }

        Div({
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
                alignItems(AlignItems.Center)
                justifyContent(JustifyContent.Center)
                height(100.vh)
                fontFamily("sans-serif")
            }
        }) {
            Div({
                style {
                    fontSize(32.px)
                    marginBottom(16.px)
                }
            }) {
                Text(uiState.formattedTime)
            }
            
            Div({
                style {
                    display(DisplayStyle.Flex)
                    flexDirection(FlexDirection.Row)
                    gap(8.px)
                }
            }) {
                Button(attrs = {
                    onClick { viewModel.onStartClicked() }
                }) {
                    Text("Start")
                }
                Button(attrs = {
                    onClick { viewModel.onStopClicked() }
                }) {
                    Text("Stop")
                }
                Button(attrs = {
                    onClick { viewModel.onCopyClicked() }
                }) {
                    Text("Copy")
                }
            }
        }
    }
}
