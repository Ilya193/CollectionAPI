package ru.kraz.collectionapi.presentation.json

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.kraz.collectionapi.domain.common.StringErrorProvider
import ru.kraz.collectionapi.domain.common.ResultFDS
import ru.kraz.collectionapi.domain.json.FetchPostsUseCase

class PostsViewModel(
    private val fetchPostsUseCase: FetchPostsUseCase,
    private val mapper: ToPostUiMapper,
    private val resourceProvider: StringErrorProvider
) : ViewModel() {

    private val posts = mutableListOf<PostUi>()
    private val _uiState = MutableStateFlow(PostUiState(isLoading = true))
    val uiState: StateFlow<PostUiState> get() = _uiState

    fun fetchPosts() = viewModelScope.launch {
        when (val res = fetchPostsUseCase()) {
            is ResultFDS.Success -> {
                posts.addAll(res.data.map { mapper.map(it) })
                _uiState.value = PostUiState(posts = posts.toList())
            }
            is ResultFDS.Error -> _uiState.value = PostUiState(error = resourceProvider.getData(res.e))
        }
    }

    fun delete(position: Int) = viewModelScope.launch(Dispatchers.IO) {
        posts.removeAt(position)
        _uiState.value = PostUiState(posts = posts.toList())
    }

    fun expand(index: Int) = viewModelScope.launch(Dispatchers.IO) {
        posts[index] = posts[index].copy(expanded = !posts[index].expanded)
        _uiState.value = PostUiState(posts = posts.toList())
    }

}
