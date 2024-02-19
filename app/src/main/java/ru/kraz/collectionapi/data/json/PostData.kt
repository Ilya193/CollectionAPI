package ru.kraz.collectionapi.data.json

import ru.kraz.collectionapi.domain.json.PostDomain

data class PostData(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
) {
    fun toPostDomain(): PostDomain =
        PostDomain(body, id, title, userId)
}