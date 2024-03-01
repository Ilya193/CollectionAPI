package ru.kraz.collectionapi.presentation.dummy

import ru.kraz.collectionapi.domain.dummy.ProductDomain
import ru.kraz.collectionapi.domain.json.PostDomain
import ru.kraz.collectionapi.domain.mars.Mapper

class ToProductUiMapper : Mapper<ProductDomain, ProductUi> {
    override fun map(data: ProductDomain): ProductUi =
        ProductUi(
            data.brand,
            data.category,
            data.description,
            data.discountPercentage,
            data.id,
            data.images,
            data.price,
            data.rating,
            data.stock,
            data.thumbnail,
            data.title
        )

}