package ru.kraz.collectionapi.presentation.json

data class PostUi(
    val id: Int,
    val body: String,
    val title: String,
    val userId: Int,
    val expanded: Boolean = false
)

data class PostUiState(
    val posts: List<PostUi> = emptyList(),
    val isLoading: Boolean = false,
    val error: Int? = null
)