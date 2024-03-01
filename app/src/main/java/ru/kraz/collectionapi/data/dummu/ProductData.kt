package ru.kraz.collectionapi.data.dummu

import ru.kraz.collectionapi.domain.dummy.ProductDomain

data class ProductData(
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
    fun toProductDomain(): ProductDomain =
        ProductDomain(
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