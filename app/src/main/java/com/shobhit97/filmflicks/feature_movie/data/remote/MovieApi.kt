package com.shobhit97.filmflicks.feature_movie.data.remote

import com.shobhit97.filmflicks.feature_movie.data.dto.movie.MovieDTO
import com.shobhit97.filmflicks.feature_movie.data.dto.movie.ResultDTO
import com.shobhit97.filmflicks.feature_movie.data.dto.movie_details.MovieDetailsDTO
import com.shobhit97.filmflicks.feature_movie.domain.model.movie_details.MovieDetailsModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

//    @GET("movie/{type}")
//    suspend fun getMoviesByTypePagination(
//        @Path("type") type: String = "now_playing",
//        @Query("page") page: Int = 1
//    ): MovieDTO

    @GET("movie/{type}")
    suspend fun getMoviesByType(
        @Path("type") type: String = "now_playing",
        @Query("page") page: Int = 1
    ): MovieDTO


    @GET("movie/{movie_id}")
    suspend fun getMovieById(@Path("movie_id") id: String): Response<MovieDetailsDTO>

    @GET("search/movie")
    suspend fun searchMovie(@Query("query") title: String,
                            @Query("page") page: Int = 1): MovieDTO

}