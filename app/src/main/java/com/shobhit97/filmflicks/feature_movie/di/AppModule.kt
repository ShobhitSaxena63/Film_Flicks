package com.shobhit97.filmflicks.feature_movie.di

import com.shobhit97.filmflicks.BuildConfig
import com.shobhit97.filmflicks.feature_movie.data.remote.MovieApi
import com.shobhit97.filmflicks.feature_movie.data.repository.MovieRepositoryImpl
import com.shobhit97.filmflicks.feature_movie.domain.repository.MovieRepository
import com.shobhit97.filmflicks.feature_movie.domain.use_case.GetMovie
import com.shobhit97.filmflicks.feature_movie.domain.use_case.GetMovies
import com.shobhit97.filmflicks.feature_movie.domain.use_case.MovieUseCases
import com.shobhit97.filmflicks.feature_movie.domain.use_case.SearchMovie
import com.shobhit97.filmflicks.util.AppConstants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val originalHttpUrl = originalRequest.url
            // Create a new URL with the added query parameter
            val newHttpUrl = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                .build()
            Timber.d("Url -> $newHttpUrl || $originalHttpUrl}")
            // Create a new request with the modified URL
            val newRequest = originalRequest.newBuilder()
                .url(newHttpUrl)
                .build()
            chain.proceed(newRequest)
        }

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(retrofit:Retrofit) : MovieApi {
        return retrofit.create(MovieApi::class.java)
    }


    @Provides
    @Singleton
    fun provideMovieRepository(movieApi: MovieApi) : MovieRepository {
        return MovieRepositoryImpl(movieApi)
    }

    @Provides
    @Singleton
    fun provideMovieUseCases(repository: MovieRepository):MovieUseCases {
        return MovieUseCases(
            getMovie = GetMovie(repository),
            getMovies = GetMovies(repository),
            searchMovie = SearchMovie(repository)
            )
    }



}

