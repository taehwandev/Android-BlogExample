package tech.thdev.architecture.app.feature.model

data class MainUiState(
    val buttonEndEvent: String,
) {

    companion object {

        val Default = MainUiState(
            buttonEndEvent = "",
        )
    }
}
