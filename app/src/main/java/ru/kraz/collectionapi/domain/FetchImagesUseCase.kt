package ru.kraz.collectionapi.domain

class FetchImagesUseCase(
    private val repository: MarsRepository
) {

    suspend operator fun invoke(): ResultFDS<List<ImageDomain>> =
        repository.fetchImages()
}