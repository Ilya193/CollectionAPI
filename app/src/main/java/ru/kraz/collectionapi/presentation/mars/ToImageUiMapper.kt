package ru.kraz.collectionapi.presentation.mars

import ru.kraz.collectionapi.domain.mars.ImageDomain
import ru.kraz.collectionapi.domain.mars.Mapper

class ToImageUiMapper : Mapper<ImageDomain, ImageUi> {
    override fun map(data: ImageDomain): ImageUi =
        ImageUi(data.id, data.img)
}