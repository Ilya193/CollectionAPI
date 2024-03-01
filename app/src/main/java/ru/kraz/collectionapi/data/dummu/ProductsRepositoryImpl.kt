package ru.kraz.collectionapi.data.dummu

import ru.kraz.collectionapi.domain.common.ResultFDS
import ru.kraz.collectionapi.domain.dummy.ProductDomain
import ru.kraz.collectionapi.domain.dummy.ProductsRepository

class ProductsRepositoryImpl(
    private val service: DummyService
) : ProductsRepository {
    override suspend fun fetchProducts(): ResultFDS<List<ProductDomain>> {
        return handleExceptions {
            val products = service.fetchProducts()
            ResultFDS.Success(products.toProductsData().map { it.toProductDomain() })
        }
    }
}