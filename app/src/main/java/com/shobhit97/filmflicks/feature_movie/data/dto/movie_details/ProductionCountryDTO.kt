package com.shobhit97.filmflicks.feature_movie.data.dto.movie_details

import com.google.gson.annotations.SerializedName

data class ProductionCountryDTO(
    @SerializedName("iso_3166_1")
    val iso31661: String,
    val name: String
) {
    fun toProductionCountryModel():ProductionCountryModel {
        return ProductionCountryModel(
            iso31661 = iso31661,
            name = name
        )
    }
}