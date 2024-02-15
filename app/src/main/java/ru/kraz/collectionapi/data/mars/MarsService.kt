package ru.kraz.collectionapi.data.mars

import retrofit2.http.GET

interface MarsService {
    @GET("/photos")
    suspend fun fetchImages(): List<ImageCloud>
}