package com.shobhit97.filmflicks.feature_movie.presentation.movie_detail

import com.shobhit97.filmflicks.feature_movie.domain.model.movie_details.MovieDetailsModel

data class MovieDetailState(
    val movie:MovieDetailsModel? = null,
    val isLoading:Boolean = false,
    val error:String? = null
)