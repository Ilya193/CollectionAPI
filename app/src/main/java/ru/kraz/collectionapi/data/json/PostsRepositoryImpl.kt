package ru.kraz.collectionapi.data.json

import ru.kraz.collectionapi.domain.common.ResultFDS
import ru.kraz.collectionapi.domain.json.PostDomain
import ru.kraz.collectionapi.domain.json.PostsRepository

class PostsRepositoryImpl(
    private val service: PostsService
) : PostsRepository {
    override suspend fun fetchPosts(): ResultFDS<List<PostDomain>> {
        return handleExceptions {
            val posts = service.fetchPosts()
            ResultFDS.Success(posts.map { it.toPostData().toPostDomain() })
        }
    }
}