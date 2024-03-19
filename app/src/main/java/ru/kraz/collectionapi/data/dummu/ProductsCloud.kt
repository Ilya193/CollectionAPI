package ru.kraz.collectionapi.data.dummu

import kotlinx.serialization.Serializable

@Serializable
data class ProductsCloud(
    val limit: Int,
    val products: List<ProductCloud>,
    val skip: Int,
    val total: Int
) {
    fun toProductsData(): List<ProductData> =
        products.map {
            it.toProductData()
        }
}