// Use case provide connection to repository level

internal sealed class FavoriteNameUseCase {

    class Observe(
        private val repository: FavoriteNameRepository
    ) : UseCase<Unit, Flow<Favorite.Name>> {
        override fun invoke(input: Unit) = repository.observe()
    }

    class Load(
        private val repository: FavoriteNameRepository
    ) : UseCase<Unit, Favorite.Name> {
        override fun invoke(input: Unit) = repository.load()
    }

    class Store(
        private val repository: FavoriteNameRepository
    ) : UseCase<Favorite.Name, Unit> {
        override fun invoke(input: Favorite.Name) = repository.store(input)
    }

    class Reset(private val repository: FavoriteNameRepository) : UseCase<Unit, Unit> {
        override fun invoke(input: Unit) = repository.reset()
    }
}
