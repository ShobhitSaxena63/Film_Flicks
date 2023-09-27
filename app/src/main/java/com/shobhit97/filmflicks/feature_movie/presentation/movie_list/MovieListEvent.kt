package com.shobhit97.filmflicks.feature_movie.presentation.movie_list

sealed class MovieListEvent {
    data class GetMovies(val type:Types):MovieListEvent()
    data class SearchMovie(val title:String):MovieListEvent()

}


enum class Types(val type:String) {
    NOW_PLAYING("now_playing"), POPULAR("popular"), TOP_RATED("top_rated"),
    UPCOMING("upcoming")
}