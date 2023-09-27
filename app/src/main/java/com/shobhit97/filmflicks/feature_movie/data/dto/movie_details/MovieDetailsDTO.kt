package com.shobhit97.filmflicks.feature_movie.data.dto.movie_details

import com.google.gson.annotations.SerializedName
import com.shobhit97.filmflicks.feature_movie.domain.model.movie_details.MovieDetailsModel

data class MovieDetailsDTO(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
//    @SerializedName("belongs_to_collection")
//    val belongsToCollection: BelongsToCollectionDTO,
    val budget: Int,
    val genres: List<GenreDTO>,
    val homepage: String,
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyDTO>,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountryDTO>,
    @SerializedName("release_date")
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageDTO>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
) {
    fun toMovieDetailsModel() : MovieDetailsModel {
        return MovieDetailsModel(
            adult = adult,
            backdropPath = backdropPath,
//            belongsToCollection = belongsToCollection.toBelongsToCollectionModel(),
            budget = budget,
            genres = genres.map { it.toGenreModel() },
            homepage = homepage,
            id = id,
            imdbId = imdbId,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            productionCompanies = productionCompanies.map { it.toProductionCompanyModel() },
            productionCountries = productionCountries.map { it.toProductionCountryModel() },
            releaseDate = releaseDate,
            revenue = revenue,
            runtime = runtime,
            spokenLanguages = spokenLanguages.map { it.toSpokenLanguageModel() },
            status = status,
            tagline = tagline,
            title = title,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount,

        )
    }
}