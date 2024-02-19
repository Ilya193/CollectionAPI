package ru.kraz.collectionapi.data.mars

import ru.kraz.collectionapi.domain.mars.ImageDomain

data class ImageData(
    val id: String,
    val img: String
) {
    fun toImageDomain(): ImageDomain =
        ImageDomain(id, img)
}