package ru.kraz.collectionapi.presentation.mars

data class ImageUi(
    val id: String,
    val img: String
)

data class ImageUiState(
    val images: List<ImageUi> = emptyList(),
    val isLoading: Boolean = false,
    val error: Int? = null
)