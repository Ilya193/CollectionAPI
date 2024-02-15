package ru.kraz.collectionapi.domain

interface MarsRepository {
    suspend fun fetchImages(): ResultFDS<List<ImageDomain>>
}