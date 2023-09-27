package com.shobhit97.filmflicks.feature_movie.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shobhit97.filmflicks.feature_movie.data.remote.MovieApi
import com.shobhit97.filmflicks.feature_movie.domain.model.movie.ResultModel
import java.net.UnknownHostException

class SearchMoviePagingSource(
    private val movieApi: MovieApi,
    private val title: String
) : PagingSource<Int, ResultModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultModel> {
        return try {
            val position = params.key ?: 1
            val response = movieApi.searchMovie(title, position)
            LoadResult.Page(
                data = response.results.map { it.toResultModel() },
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.totalPages) null else position + 1
            )
        } catch (ex: UnknownHostException) {
            LoadResult.Error(ex)

        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ResultModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}
