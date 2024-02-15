package ru.kraz.collectionapi.data.mars

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageCloud(
    val id: String,
    @SerialName(value = "img_src")
    val img: String
) {
    fun toImageData(): ImageData =
        ImageData(id, img)
}