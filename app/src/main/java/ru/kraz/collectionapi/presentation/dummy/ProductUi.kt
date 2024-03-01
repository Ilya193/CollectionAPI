package ru.kraz.collectionapi.presentation.dummy

data class ProductUi(
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
    val title: String
)

data class ProductUiState(
    val products: List<ProductUi> = emptyList(),
    val isLoading: Boolean = false,
    val error: Int? = null
)