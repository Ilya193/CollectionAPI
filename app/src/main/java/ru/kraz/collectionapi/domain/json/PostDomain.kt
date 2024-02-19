package ru.kraz.collectionapi.domain.json

data class PostDomain(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)