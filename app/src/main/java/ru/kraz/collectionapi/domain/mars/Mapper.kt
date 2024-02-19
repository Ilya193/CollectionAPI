package ru.kraz.collectionapi.domain.mars

interface Mapper<T, R> {
    fun map(data: T): R
}