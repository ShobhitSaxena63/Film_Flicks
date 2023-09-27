package com.shobhit97.filmflicks.feature_movie.domain.repository

import androidx.paging.PagingData
import com.shobhit97.filmflicks.feature_movie.data.dto.movie.MovieDTO
import com.shobhit97.filmflicks.feature_movie.data.dto.movie.ResultDTO
import com.shobhit97.filmflicks.feature_movie.domain.model.movie.MovieModel
import com.shobhit97.filmflicks.feature_movie.domain.model.movie.ResultModel
import com.shobhit97.filmflicks.feature_movie.domain.model.movie_details.MovieDetailsModel
import com.shobhit97.filmflicks.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MovieRepository {

    fun getMoviesByType(type:String):Flow<PagingData<ResultModel>>

    fun searchMovie(title:String) :Flow<PagingData<ResultModel>>

    fun getMovieById(id:String): Flow<Resource<MovieDetailsModel>>

}