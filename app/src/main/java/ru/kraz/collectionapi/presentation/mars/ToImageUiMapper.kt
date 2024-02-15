package ru.kraz.collectionapi.presentation.mars

import ru.kraz.collectionapi.domain.ImageDomain
import ru.kraz.collectionapi.domain.Mapper

class ToImageUiMapper : Mapper<ImageDomain, ImageUi> {
    override fun map(data: ImageDomain): ImageUi =
        ImageUi(data.id, data.img)
}