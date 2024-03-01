package ru.kraz.collectionapi.data.dummu

import retrofit2.http.GET

interface DummyService {
    @GET("products")
    suspend fun fetchProducts(): ProductsCloud
}