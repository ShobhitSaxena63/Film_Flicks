package com.shobhit97.filmflicks.feature_movie.data.dto.movie_details

import com.google.gson.annotations.SerializedName
import com.shobhit97.filmflicks.feature_movie.domain.model.movie_details.SpokenLanguageModel

data class SpokenLanguageDTO(
    @SerializedName("english_name")
    val englishName: String,
    @SerializedName("iso_639_1")
    val iso6391: String,
    val name: String
) {
    fun toSpokenLanguageModel(): SpokenLanguageModel {
        return SpokenLanguageModel(
            englishName = englishName,
            iso6391 = iso6391,
            name = name
        )
    }
}