package ru.kraz.collectionapi.presentation.common

import ru.kraz.collectionapi.R
import ru.kraz.collectionapi.domain.common.ErrorType
import ru.kraz.collectionapi.domain.common.StringErrorProvider

class BaseStringErrorProvider : StringErrorProvider {
    override fun getData(e: ErrorType): Int {
        return when (e) {
            ErrorType.NO_CONNECTION -> R.string.no_connection
            ErrorType.GENERIC_ERROR -> R.string.something_went_wrong
        }
    }
}