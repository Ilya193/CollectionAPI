package ru.kraz.collectionapi.domain.dummy

import ru.kraz.collectionapi.domain.common.ResultFDS

class FetchProductsUseCase(
    private val repository: ProductsRepository
) {

    suspend operator fun invoke(): ResultFDS<List<ProductDomain>> =
        repository.fetchProducts()
}