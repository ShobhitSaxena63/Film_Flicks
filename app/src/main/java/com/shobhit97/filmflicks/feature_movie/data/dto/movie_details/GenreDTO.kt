package com.shobhit97.filmflicks.feature_movie.data.dto.movie_details

import com.shobhit97.filmflicks.feature_movie.domain.model.movie_details.GenreModel

data class GenreDTO(
    val id: Int,
    val name: String
) {
    fun toGenreModel():GenreModel {
        return GenreModel(
            id = id,
            name = name
        )
    }
}