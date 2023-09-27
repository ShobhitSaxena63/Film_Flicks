package com.shobhit97.filmflicks.feature_movie.data.dto.movie_details

import com.google.gson.annotations.SerializedName

data class ProductionCompanyModel(
    val id: Int,
    val logoPath: String?,
    val name: String,
    val originCountry: String
)