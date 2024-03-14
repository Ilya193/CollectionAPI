package ru.kraz.collectionapi.domain.common

enum class ErrorType {
    NO_CONNECTION,
    GENERIC_ERROR
}

sealed class Error {
    data object NoConnection : Error()
    data object GenericError: Error()
}