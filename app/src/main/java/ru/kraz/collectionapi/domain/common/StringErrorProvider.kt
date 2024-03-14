package ru.kraz.collectionapi.domain.common

interface StringErrorProvider {
    fun getData(e: ErrorType): Int
}