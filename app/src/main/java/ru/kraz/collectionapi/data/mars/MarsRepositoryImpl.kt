package ru.kraz.collectionapi.data.mars

import ru.kraz.collectionapi.domain.common.ResultFDS
import ru.kraz.collectionapi.domain.mars.ImageDomain
import ru.kraz.collectionapi.domain.mars.MarsRepository

class MarsRepositoryImpl(
    private val service: MarsService
) : MarsRepository {
    override suspend fun fetchImages(): ResultFDS<List<ImageDomain>> {
        return handleExceptions {
            val images = service.fetchImages()
            ResultFDS.Success(images.map { it.toImageData().toImageDomain() })
        }
    }
}