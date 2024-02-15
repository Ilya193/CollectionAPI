package ru.kraz.collectionapi.domain

interface Mapper<T, R> {
    fun map(data: T): R
}