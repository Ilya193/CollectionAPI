package ru.kraz.collectionapi.domain.mars

import ru.kraz.collectionapi.domain.common.ResultFDS

class FetchImagesUseCase(
    private val repository: MarsRepository
) {

    suspend operator fun invoke(): ResultFDS<List<ImageDomain>> =
        repository.fetchImages()
}