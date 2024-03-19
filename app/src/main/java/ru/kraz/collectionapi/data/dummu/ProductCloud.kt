package ru.kraz.collectionapi.data.dummu

import kotlinx.serialization.Serializable

@Serializable
data class ProductCloud(
    val brand: String,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    val title: String,
) {
    fun toProductData(): ProductData =
        ProductData(
            brand,
            category,
            description,
            discountPercentage,
            id,
            images,
            price,
            rating,
            stock,
            thumbnail,
            title
        )
}