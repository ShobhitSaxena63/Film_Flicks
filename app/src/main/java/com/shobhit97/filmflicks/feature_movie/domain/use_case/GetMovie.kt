package com.shobhit97.filmflicks.feature_movie.domain.use_case

import com.shobhit97.filmflicks.feature_movie.domain.model.movie.MovieModel
import com.shobhit97.filmflicks.feature_movie.domain.model.movie_details.MovieDetailsModel
import com.shobhit97.filmflicks.feature_movie.domain.repository.MovieRepository
import com.shobhit97.filmflicks.util.Resource
import kotlinx.coroutines.flow.Flow

class GetMovie(
    private val repository: MovieRepository
) {
    operator fun invoke(id:String):Flow<Resource<MovieDetailsModel>> {
        return repository.getMovieById(id)
    }
}