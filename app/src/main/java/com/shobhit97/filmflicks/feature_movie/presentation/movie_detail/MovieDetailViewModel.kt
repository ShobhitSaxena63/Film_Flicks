package com.shobhit97.filmflicks.feature_movie.presentation.movie_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shobhit97.filmflicks.feature_movie.domain.use_case.MovieUseCases
import com.shobhit97.filmflicks.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(MovieDetailState())
    val state: State<MovieDetailState> = _state

    init {
        savedStateHandle.get<Int>("movieId")?.let { movieId ->
            if(movieId != -1) {
                    movieUseCases.getMovie(movieId.toString()).onEach {movie ->
                        when(movie) {
                            is Resource.Error -> {
                                _state.value = MovieDetailState(
                                    error = movie.message
                                )
                            }
                            is Resource.Loading -> {
                                _state.value = MovieDetailState(
                                    isLoading = true
                                )
                            }
                            is Resource.Success -> {
                                _state.value =MovieDetailState(
                                    movie = movie.data
                                )
                            }
                        }

                    }.launchIn(viewModelScope)

                }
            }

        }
    }

