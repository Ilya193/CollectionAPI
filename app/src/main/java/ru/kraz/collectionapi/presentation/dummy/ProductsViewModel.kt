package ru.kraz.collectionapi.presentation.dummy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.kraz.collectionapi.domain.common.StringErrorProvider
import ru.kraz.collectionapi.domain.common.ResultFDS
import ru.kraz.collectionapi.domain.dummy.FetchProductsUseCase

class ProductsViewModel(
    private val fetchProductsUseCase: FetchProductsUseCase,
    private val mapper: ToProductUiMapper,
    private val resourceProvider: StringErrorProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductUiState(isLoading = true))
    val uiState: StateFlow<ProductUiState> get() = _uiState

    fun fetchProducts() = viewModelScope.launch {
        when (val res = fetchProductsUseCase()) {
            is ResultFDS.Success -> _uiState.value = ProductUiState(products = res.data.map { mapper.map(it) })
            is ResultFDS.Error -> _uiState.value = ProductUiState(error = resourceProvider.getData(res.e))
        }
    }
}