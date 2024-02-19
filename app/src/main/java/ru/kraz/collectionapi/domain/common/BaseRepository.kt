package ru.kraz.collectionapi.domain.common

import java.net.UnknownHostException

interface BaseRepository {
    suspend fun <T> handleExceptions(block: suspend () -> ResultFDS<T>): ResultFDS<T> {
        return try {
            block()
        } catch (e: UnknownHostException) {
            ResultFDS.Error(ErrorType.NO_CONNECTION)
        } catch (e: Exception) {
            ResultFDS.Error(ErrorType.GENERIC_ERROR)
        }
    }
}