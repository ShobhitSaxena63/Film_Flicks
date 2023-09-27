package com.shobhit97.filmflicks.feature_movie.domain.model

import com.shobhit97.filmflicks.feature_movie.presentation.movie_list.Types

data class ChipState(
    val isChecked: Boolean,
    val text: String,
    val movieType: Types
)
