// View model with state just connect UI with domain logic

internal class FavoriteNameDefinitionViewModel(
    private val observeFavoriteName: FavoriteNameUseCase.Observe,
    private val storeFavoriteName: FavoriteNameUseCase.Store,
    private val confirmFavoriteNameDefinition: ConfirmFavoriteNameDefinition,
    private val cancelFavoriteDefinition: FavoriteDefinitionUseCase.Cancel
) : StreamingViewModel<State>() {

    override fun createStates(): Flow<State> = observeFavoriteName()
        .map { favoriteName ->
            State(
                name = favoriteName.value,
                confirmEnabled = favoriteName.value.isNotBlank()
            )
        }

    fun onNameChange(name: String) {
        storeFavoriteName(Favorite.Name(name))
    }

    fun onCancel() {
        cancelFavoriteDefinition()
    }

    fun onConfirm() {
        viewModelScope.launch {
            confirmFavoriteNameDefinition()
        }
    }

    data class State(
        val name: String,
        val confirmEnabled: Boolean
    ) : ViewModelState
}
