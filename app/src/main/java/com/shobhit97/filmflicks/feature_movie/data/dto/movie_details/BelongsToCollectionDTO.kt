package com.shobhit97.filmflicks.feature_movie.data.dto.movie_details

import com.google.gson.annotations.SerializedName
import com.shobhit97.filmflicks.feature_movie.domain.model.movie_details.BelongsToCollectionModel

data class BelongsToCollectionDTO(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val id: Int,
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String
){
    fun toBelongsToCollectionModel():BelongsToCollectionModel {
        return BelongsToCollectionModel(
            backdropPath = backdropPath,
            id = id,
            name = name,
            posterPath = posterPath
        )
    }
}