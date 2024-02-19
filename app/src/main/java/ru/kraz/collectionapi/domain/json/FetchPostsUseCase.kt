package ru.kraz.collectionapi.domain.json

import ru.kraz.collectionapi.domain.common.ResultFDS

class FetchPostsUseCase(
    private val repository: PostsRepository
) {
    suspend operator fun invoke(): ResultFDS<List<PostDomain>> = repository.fetchPosts()
}
