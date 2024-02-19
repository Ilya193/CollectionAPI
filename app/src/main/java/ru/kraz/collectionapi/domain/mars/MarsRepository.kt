package ru.kraz.collectionapi.domain.mars

import ru.kraz.collectionapi.domain.common.BaseRepository
import ru.kraz.collectionapi.domain.common.ResultFDS

interface MarsRepository: BaseRepository {
    suspend fun fetchImages(): ResultFDS<List<ImageDomain>>
}