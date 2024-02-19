package ru.kraz.collectionapi.data.json

data class PostCloud(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
) {
    fun toPostData(): PostData =
        PostData(body, id, title, userId)
}