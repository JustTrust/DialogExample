// Implementation of FavoriteNameRepository interface hold a favorite item name

internal class FavoriteNameRepositoryImpl : FavoriteNameRepository {

    private val values = MutableStateFlow(EMPTY)

    override fun observe(): Flow<Favorite.Name> = values.asStateFlow()

    override fun load(): Favorite.Name = values.value

    override fun store(name: Favorite.Name) {
        values.value = name
    }

    override fun reset() {
        values.value = EMPTY
    }

    private companion object {
        val EMPTY = Favorite.Name("")
    }
}
