package com.shobhit97.filmflicks.feature_movie.domain.model.movie_details

import com.google.gson.annotations.SerializedName
import com.shobhit97.filmflicks.feature_movie.data.dto.movie_details.ProductionCompanyModel
import com.shobhit97.filmflicks.feature_movie.data.dto.movie_details.ProductionCountryModel


data class MovieDetailsModel(
    val adult: Boolean,
    val backdropPath: String?,
//    val belongsToCollection: BelongsToCollectionModel,
    val budget: Int,
    val genres: List<GenreModel>,
    val homepage: String,
    val id: Int,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val productionCompanies: List<ProductionCompanyModel>,
    val productionCountries: List<ProductionCountryModel>,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: List<SpokenLanguageModel>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)