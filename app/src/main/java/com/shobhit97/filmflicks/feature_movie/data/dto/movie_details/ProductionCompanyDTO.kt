package com.shobhit97.filmflicks.feature_movie.data.dto.movie_details

import com.google.gson.annotations.SerializedName

data class ProductionCompanyDTO(
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String?,
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
) {
    fun toProductionCompanyModel():ProductionCompanyModel {
        return ProductionCompanyModel(
            id = id,
            logoPath = logoPath,
            name = name,
            originCountry = originCountry
        )
    }
}