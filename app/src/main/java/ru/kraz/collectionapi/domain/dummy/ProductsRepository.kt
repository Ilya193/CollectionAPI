package ru.kraz.collectionapi.domain.dummy

import ru.kraz.collectionapi.domain.common.BaseRepository
import ru.kraz.collectionapi.domain.common.ResultFDS

interface ProductsRepository : BaseRepository {

    suspend fun fetchProducts(): ResultFDS<List<ProductDomain>>
}