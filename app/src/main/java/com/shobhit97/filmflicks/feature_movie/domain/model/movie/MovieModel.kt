package com.shobhit97.filmflicks.feature_movie.domain.model.movie

import com.google.gson.annotations.SerializedName
import com.shobhit97.filmflicks.feature_movie.data.dto.movie.ResultDTO


data class MovieModel(
    val page: Int,
    val results: List<ResultModel>,
    val totalPages: Int,
    val totalResults: Int
)