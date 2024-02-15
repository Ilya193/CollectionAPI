package ru.kraz.collectionapi.domain

import ru.kraz.collectionapi.R

interface ResourceProvider {
    fun getData(e: ErrorType): Int

    class Base : ResourceProvider {
        override fun getData(e: ErrorType): Int {
            return when (e) {
                ErrorType.NO_CONNECTION -> R.string.no_connection
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable
                ErrorType.GENERIC_ERROR -> R.string.something_went_wrong
            }
        }
    }
}