package com.shobhit97.filmflicks.feature_movie.presentation.movie_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.shobhit97.filmflicks.feature_movie.presentation.movie_list.components.MovieListItem
import com.shobhit97.filmflicks.feature_movie.presentation.movie_list.components.RoundedCornerSearchBar
import com.shobhit97.filmflicks.feature_movie.presentation.util.Screen
import com.shobhit97.filmflicks.feature_movie.domain.model.ChipState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    navController: NavController,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val paginatedData = viewModel.movieState.collectAsLazyPagingItems()
    val selectedChip =  viewModel.selectedChip.value


    val listState = rememberLazyGridState()
    val scope = rememberCoroutineScope()
    val chipState = remember {
        mutableStateListOf(
            ChipState
                (
                isChecked = selectedChip == Types.NOW_PLAYING,
                text = "Now Playing",
                movieType = Types.NOW_PLAYING
            ),
            ChipState(
                isChecked = selectedChip == Types.POPULAR,
                text = "Popular",
                movieType = Types.POPULAR
            ),
            ChipState(
                isChecked = selectedChip == Types.TOP_RATED,
                text = "Top Rated",
                movieType = Types.TOP_RATED
            ),
            ChipState(
                isChecked = selectedChip == Types.UPCOMING,
                text = "Upcoming",
                movieType = Types.UPCOMING
            )
        )
    }

    Column(Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(16.dp)) {
            if (paginatedData.loadState.append is LoadState.Loading) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Red,
                    trackColor = Color.Yellow
                )
            }
        }
        RoundedCornerSearchBar(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .height(50.dp), onSearch = { title ->
            viewModel.clearData()
            viewModel.onEvent(MovieListEvent.SearchMovie(title))
            scope.launch {
                listState.scrollToItem(0)
            }
            chipState.replaceAll {
                it.copy(isChecked = false)
            }

        })

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            chipState.forEachIndexed { _, chipData ->
                FilterChip(
                    shape = RoundedCornerShape(20.dp),
                    selected = chipData.isChecked,
                    onClick = {
                        chipState.replaceAll {
                            it.copy(isChecked = it.text == chipData.text)
                        }
                        viewModel.clearData()
                        viewModel.onEvent(MovieListEvent.GetMovies(chipData.movieType))
                        scope.launch {
                            listState.scrollToItem(0)
                        }

                    },
                    label = {
                        Text(
                            text = chipData.text,
                            fontSize = 10.sp,
                        )
                    },
                )
            }
        }
        Box(Modifier.fillMaxSize()) {
            if (paginatedData.loadState.refresh is LoadState.Loading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (paginatedData.loadState.append is LoadState.Error || paginatedData.loadState.refresh is LoadState.Error) {
                val errorState = when (paginatedData.loadState.append) {
                    is LoadState.Error -> paginatedData.loadState.append as LoadState.Error
                    else -> paginatedData.loadState.refresh as LoadState.Error
                }
                Text(
                    text = "Error: - ${errorState.error.localizedMessage}",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                )
            } else {
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxSize(),
                        state = listState,
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(8.dp)
                    ) {

                        items(paginatedData.itemCount) { item ->
                            paginatedData[item]?.let {
                                MovieListItem(it) { id ->
                                    navController.navigate(
                                        route = Screen.MovieDetailScreen.route +
                                                "movieId=${id}"
                                    )
                                }
                            }
                        }
                    }

            }
        }


    }

}


