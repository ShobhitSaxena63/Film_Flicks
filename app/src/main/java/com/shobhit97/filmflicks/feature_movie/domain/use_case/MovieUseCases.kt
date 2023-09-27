package com.shobhit97.filmflicks.feature_movie.domain.use_case

data class MovieUseCases(
    val getMovies:GetMovies,
    val searchMovie: SearchMovie,
    val getMovie:GetMovie,
)