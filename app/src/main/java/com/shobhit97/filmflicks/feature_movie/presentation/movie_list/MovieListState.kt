package com.shobhit97.filmflicks.feature_movie.presentation.movie_list

import androidx.paging.PagingData
import com.shobhit97.filmflicks.feature_movie.domain.model.movie.MovieModel
import com.shobhit97.filmflicks.feature_movie.domain.model.movie.ResultModel
import kotlinx.coroutines.flow.Flow

data class MovieListState(
    val movies:MovieModel? = null,
    val isLoading:Boolean = false,
    val error: String? = null,
//    var pagingData:PagingData<ResultModel>? = null
    var pagingData: Flow<PagingData<ResultModel>>? = null
)