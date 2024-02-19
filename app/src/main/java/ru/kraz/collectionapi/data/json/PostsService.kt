package ru.kraz.collectionapi.data.json

import retrofit2.http.GET

interface PostsService {

    @GET("posts")
    suspend fun fetchPosts(): List<PostCloud>
}