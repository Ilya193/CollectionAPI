package ru.kraz.collectionapi.presentation.common

sealed class Screens(val route: String) {

    data object Main: Screens( "Main")
    data object MarsAPI: Screens("MarsAPI")
    data object JsonAPI: Screens("JsonAPI")
}