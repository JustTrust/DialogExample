internal interface FavoriteNameRepository {
    fun observe(): Flow<Favorite.Name>
    fun load(): Favorite.Name
    fun store(name: Favorite.Name)
    fun reset()
}
