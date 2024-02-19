package ru.kraz.collectionapi.presentation.json

import ru.kraz.collectionapi.domain.json.PostDomain
import ru.kraz.collectionapi.domain.mars.Mapper

class ToPostUiMapper : Mapper<PostDomain, PostUi> {
    override fun map(data: PostDomain): PostUi =
        PostUi(data.id, data.body, data.title, data.userId)

}