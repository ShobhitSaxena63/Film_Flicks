package com.shobhit97.filmflicks.feature_movie.presentation.util

import com.shobhit97.filmflicks.feature_movie.data.dto.movie_details.ProductionCompanyModel
import com.shobhit97.filmflicks.feature_movie.domain.model.movie_details.GenreModel
import java.text.SimpleDateFormat
import java.util.Locale


fun String.toLanguageName(): String {
    return when (this) {
        "en" -> "English"
        "es" -> "Spanish"
        "fr" -> "French"
        "de" -> "German"
        "hi" -> "Hindi"
        else -> this
    }
}


fun String.toChangeDateFormat():String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return try {
        // Parse the input date string into a Date object
        val date = inputFormat.parse(this)

        // Format the Date object into the desired output format
        outputFormat.format(date)
    } catch (e: Exception) {
        // Handle parsing or formatting errors here
        "Invalid Date"
    }

}

fun List<ProductionCompanyModel>.getProductionName():String {
    var productionName = ""
    if(this.isNotEmpty()) {
        this.forEachIndexed { index, productionCompanyModel ->
            productionName += if(index == this.size-1) {
                productionCompanyModel.name
            } else {
                productionCompanyModel.name + " | "
            }
        }
    }

    return productionName
}

fun List<GenreModel>.getCategories():String{
    var categories = ""
    if(this.isNotEmpty()) {
        this.forEachIndexed { index, genreModel ->
            categories += if(index == this.size-1) {
                genreModel.name
            } else {
                genreModel.name + " | "
            }
        }
    }
    return categories

}