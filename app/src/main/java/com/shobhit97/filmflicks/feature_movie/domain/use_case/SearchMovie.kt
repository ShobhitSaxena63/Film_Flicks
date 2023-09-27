package com.shobhit97.filmflicks.feature_movie.domain.use_case

import androidx.paging.PagingData
import com.shobhit97.filmflicks.feature_movie.domain.model.movie.MovieModel
import com.shobhit97.filmflicks.feature_movie.domain.model.movie.ResultModel
import com.shobhit97.filmflicks.feature_movie.domain.repository.MovieRepository
import com.shobhit97.filmflicks.util.Resource
import kotlinx.coroutines.flow.Flow

class SearchMovie(
    private val repository: MovieRepository
) {
     operator fun invoke(title:String): Flow<PagingData<ResultModel>> {
         return repository.searchMovie(title)
     }
}