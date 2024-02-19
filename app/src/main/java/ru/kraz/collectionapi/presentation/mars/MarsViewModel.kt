package ru.kraz.collectionapi.presentation.mars

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.kraz.collectionapi.domain.mars.FetchImagesUseCase
import ru.kraz.collectionapi.domain.common.ResourceProvider
import ru.kraz.collectionapi.domain.common.ResultFDS

class MarsViewModel(
    private val fetchImagesUseCase: FetchImagesUseCase,
    private val mapper: ToImageUiMapper,
    private val resourceProvider: ResourceProvider
): ViewModel() {

    private val _uiState = MutableStateFlow(ImageUiState(isLoading = true))
    val uiState: StateFlow<ImageUiState> get() = _uiState

    fun fetchImages() = viewModelScope.launch {
        _uiState.value = ImageUiState(isLoading = true)
        when (val res = fetchImagesUseCase()) {
            is ResultFDS.Success -> _uiState.value = ImageUiState(images = res.data.map { mapper.map(it) })
            is ResultFDS.Error -> _uiState.value = ImageUiState(error = resourceProvider.getData(res.e))
        }
    }
}