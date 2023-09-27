package com.shobhit97.filmflicks.feature_movie.data.dto.movie

import com.google.gson.annotations.SerializedName
import com.shobhit97.filmflicks.feature_movie.domain.model.movie.MovieModel

data class MovieDTO(
    val page: Int,
    val results: List<ResultDTO>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) {
    fun toMovieModel():MovieModel {
        return MovieModel(
            page = page,
            results = results.map { it.toResultModel() },
            totalPages = totalPages,
            totalResults = totalResults
        )
    }
}