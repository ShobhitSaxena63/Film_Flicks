package com.shobhit97.filmflicks.feature_movie.domain.use_case

import androidx.paging.PagingData
import com.shobhit97.filmflicks.feature_movie.domain.model.movie.MovieModel
import com.shobhit97.filmflicks.feature_movie.domain.model.movie.ResultModel
import com.shobhit97.filmflicks.feature_movie.domain.repository.MovieRepository
import com.shobhit97.filmflicks.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import java.net.UnknownHostException

class GetMovies(
    private val repository: MovieRepository
) {
    operator fun invoke(type:String):  Flow<PagingData<ResultModel>> {
        return repository.getMoviesByType(type)
    }
}