package com.shobhit97.filmflicks.feature_movie.presentation.util

sealed class Screen(val route:String) {
    object MovieListScreen:Screen("movie_list_screen")
    object MovieDetailScreen:Screen("movie_detail_screen")
}