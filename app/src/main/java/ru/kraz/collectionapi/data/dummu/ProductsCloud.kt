package ru.kraz.collectionapi.data.dummu

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