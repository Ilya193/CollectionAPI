package ru.kraz.collectionapi.presentation.json

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.kraz.collectionapi.domain.common.ResultFDS
import ru.kraz.collectionapi.domain.common.StringErrorProvider
import ru.kraz.collectionapi.domain.json.FetchPostsUseCase

class PostsViewModel(
    private val fetchPostsUseCase: FetchPostsUseCase,
    private val mapper: ToPostUiMapper,
    private val resourceProvider: StringErrorProvider,
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

            is ResultFDS.Error -> _uiState.value =
                PostUiState(error = resourceProvider.getData(res.e))
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

/*
interface Action

sealed interface PostsAction : Action {
    data object Loading : PostsAction
    data class Show(val index: Int) : PostsAction
}

interface State

data class PostsState(
    val posts: List<PostUi> = emptyList(),
    val isLoading: Boolean = false,
    val error: Int? = null,
) : State

interface Reducer<S : State, A : Action> {
    fun reduce(state: S, action: A): S
}

class PostsReducer : Reducer<PostsState, PostsAction> {
    override fun reduce(state: PostsState, action: PostsAction): PostsState {
        return when (action) {
            is PostsAction.Show -> {
                val posts = state.posts.toMutableList()
                posts[action.index] =
                    posts[action.index].copy(expanded = !posts[action.index].expanded)
                state.copy(posts = posts)
            }
        }
    }

}

abstract class BaseViewModel<S : State, A : Action>(
    protected val reducer: Reducer<S, A>,
    initState: S,
) : ViewModel() {

    private val action = MutableSharedFlow<A>(extraBufferCapacity = 128)

    var uiState: S by mutableStateOf(initState)
        private set

    init {
        viewModelScope.launch {
            action.collect {
                uiState = reducer.reduce(uiState, it)
            }
        }
    }

    protected fun emit(newAction: A) {
        action.tryEmit(newAction)
    }
}

class TestViewModel :
    BaseViewModel<PostsState, PostsAction>(reducer = PostsReducer(), initState = PostsState()) {

    fun show(index: Int) = emit(PostsAction.Show(index))
}*/
