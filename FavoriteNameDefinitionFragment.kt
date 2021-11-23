// Dialog with two buttons and an edit text field just guide events to a view model.

internal class FavoriteNameDefinitionFragment :
    BaseDialogFragment(R.layout.favorite_name_fragment) {

    private val viewModel: FavoriteNameDefinitionViewModel by viewModel()
    private val favoriteName: TextInputLayout by bindView(R.id.favorite_name)
    private val confirm: Button by bindView(R.id.confirm_favorite_name)
    private val cancel: Button by bindView(R.id.cancel_favorite_name)

    override fun onInitializeViews() {
        favoriteName.requestDelayedFocus()
    }

    override suspend fun onBindStates() {
        viewModel.bindStates { state ->
            favoriteName.editText?.textString = state.name
            confirm.isEnabled = state.confirmEnabled
        }
    }

    override suspend fun onBindViews() {
        coroutineScope {
            launch { favoriteName.editText?.textChangesDebounce { viewModel.onNameChange(it) } }
            launch { confirm.clicks { viewModel.onConfirm() } }
            launch { cancel.clicks { viewModel.onCancel() } }
        }
    }
}
