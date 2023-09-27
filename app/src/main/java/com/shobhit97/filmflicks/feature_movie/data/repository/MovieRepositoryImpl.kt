package com.shobhit97.filmflicks.feature_movie.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shobhit97.filmflicks.feature_movie.data.paging.GetMoviePagingSource
import com.shobhit97.filmflicks.feature_movie.data.paging.SearchMoviePagingSource
import com.shobhit97.filmflicks.feature_movie.data.remote.MovieApi
import com.shobhit97.filmflicks.feature_movie.domain.model.movie.MovieModel
import com.shobhit97.filmflicks.feature_movie.domain.model.movie.ResultModel
import com.shobhit97.filmflicks.feature_movie.domain.model.movie_details.MovieDetailsModel
import com.shobhit97.filmflicks.feature_movie.domain.repository.MovieRepository
import com.shobhit97.filmflicks.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.UnknownHostException

class MovieRepositoryImpl(
    private val movieApi: MovieApi
) : MovieRepository {

    override fun getMoviesByType(type:String): Flow<PagingData<ResultModel>> = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { GetMoviePagingSource(movieApi,type) }
    ).flow


    override fun getMovieById(id: String): Flow<Resource<MovieDetailsModel>> {
        return flow {
            emit(Resource.Loading())
            try {
                val result = movieApi.getMovieById(id)
                if (result.isSuccessful) {
                    emit(Resource.Success(result.body()?.toMovieDetailsModel()))
                } else {
                    emit(Resource.Error(result.errorBody().toString()))
                }
            } catch (ex: UnknownHostException) {
                emit(Resource.Error(ex.localizedMessage ?: "Unknown Host Exception"))

            } catch (ex: Exception) {
                emit(Resource.Error(ex.localizedMessage ?: "Something Went Wrong"))
            }

        }.flowOn(Dispatchers.IO)
    }

    override fun searchMovie(title: String): Flow<PagingData<ResultModel>> {
        return Pager(
            config = PagingConfig(pageSize = 20, maxSize = 100),
            pagingSourceFactory = {SearchMoviePagingSource(movieApi,title)},
            ).flow
    }


}