package com.shobhit97.filmflicks.feature_movie.presentation.movie_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.map
import com.shobhit97.filmflicks.feature_movie.data.dto.movie.ResultDTO
import com.shobhit97.filmflicks.feature_movie.domain.model.movie.ResultModel
import com.shobhit97.filmflicks.feature_movie.domain.repository.MovieRepository
import com.shobhit97.filmflicks.feature_movie.domain.use_case.MovieUseCases
import com.shobhit97.filmflicks.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val movieUseCases: MovieUseCases) :
    ViewModel() {

    private val _movieState = MutableStateFlow<PagingData<ResultModel>>(PagingData.empty())
    val movieState: MutableStateFlow<PagingData<ResultModel>> = _movieState

    private val _selectedChip = mutableStateOf(Types.NOW_PLAYING)
    val selectedChip: State<Types> = _selectedChip


    init {
        onEvent(MovieListEvent.GetMovies(Types.NOW_PLAYING))
    }

    fun onEvent(event: MovieListEvent) {
        when (event) {
            is MovieListEvent.GetMovies -> {
                viewModelScope.launch {
                    _selectedChip.value  = event.type
                    movieUseCases.getMovies(event.type.type).distinctUntilChanged()
                        .cachedIn(viewModelScope).collect {
                        _movieState.value = it
                    }
                }

            }

            is MovieListEvent.SearchMovie -> {
                viewModelScope.launch {
                    movieUseCases.searchMovie(event.title).distinctUntilChanged()
                        .cachedIn(viewModelScope).collect {
                        _movieState.value = it
                    }
                }
            }

        }
    }


    fun clearData() {
        _movieState.value = PagingData.empty()
    }

}