package ru.kraz.collectionapi.domain.json

import ru.kraz.collectionapi.domain.common.BaseRepository
import ru.kraz.collectionapi.domain.common.ResultFDS

interface PostsRepository: BaseRepository {
    suspend fun fetchPosts(): ResultFDS<List<PostDomain>>
}
