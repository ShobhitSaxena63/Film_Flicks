package com.shobhit97.filmflicks.feature_movie.presentation.movie_detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.shobhit97.filmflicks.R
import com.shobhit97.filmflicks.feature_movie.presentation.util.getCategories
import com.shobhit97.filmflicks.feature_movie.presentation.util.getProductionName
import com.shobhit97.filmflicks.feature_movie.presentation.util.toChangeDateFormat
import com.shobhit97.filmflicks.feature_movie.presentation.util.toLanguageName
import com.shobhit97.filmflicks.util.AppConstants

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (state.error != null) {
            Text(
                text = state.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.Center)
            )
        } else {
            state.movie?.let { movie ->
                Column(Modifier.verticalScroll(rememberScrollState())) {
                    Box {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .height(320.dp)
                        ) {
                            AsyncImage(
                                model = AppConstants.BACKDROP_BASE_URL + movie.backdropPath,
                                contentDescription = movie.originalTitle,
                                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                                error = painterResource(id = R.drawable.ic_launcher_foreground),
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(220.dp)
                            )


                            AsyncImage(
                                model = AppConstants.POSTER_BASE_URL + movie.posterPath,
                                contentDescription = movie.originalTitle,
                                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                                error = painterResource(id = R.drawable.ic_launcher_foreground),
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .padding(start = 22.dp, end = 16.dp)
                                    .size(width = 140.dp, height = 160.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .border(
                                        BorderStroke(
                                            width = 2.dp,
                                            color = Color.White
                                        ),
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .align(Alignment.BottomStart)
                            )


                        }

                        Column(
                            modifier = Modifier
                                .padding(top = 230.dp, end = 16.dp, start = 178.dp)
                                .align(Alignment.BottomStart)
                        ) {
                            Text(
                                text = movie.originalTitle,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily(
                                    Font(R.font.mooli_regular)
                                )
                            )
                            Text(
                                text = movie.tagline,
                                fontSize = 14.sp,
                                color = Color.Gray,
                                fontFamily = FontFamily.SansSerif,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(top = 4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = stringResource(id = R.string.rating_icon),
                                    tint = Color(
                                        0xFFB3A105
                                    )
                                )
                                Text(
                                    text = stringResource(id = R.string.rating, movie.voteAverage),
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    modifier = Modifier.padding(start = 4.dp)
                                )
                            }

                        }
                    }



                    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                        Text(
                            text = stringResource(id = R.string.overview),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily.SansSerif,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                        Text(
                            text = movie.overview,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Justify,
                            fontWeight = FontWeight.Light,
                            fontFamily = FontFamily.SansSerif,
                            modifier = Modifier.padding(start = 4.dp, top = 6.dp)
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.language),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily.SansSerif,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                imageVector = Icons.Default.Language,
                                contentDescription = stringResource(id = R.string.language_icon)
                            )
                            Text(
                                text = movie.originalLanguage.toLanguageName(),
                                fontSize = 16.sp,
                                fontFamily = FontFamily.SansSerif,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.release_date),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily.SansSerif,
                            modifier = Modifier.padding(start = 4.dp)
                        )

                        Text(
                            text = movie.releaseDate.toChangeDateFormat(),
                            fontSize = 16.sp,
                            fontFamily = FontFamily.SansSerif,
                            modifier = Modifier.padding(start = 4.dp)
                        )

                    }

                    if (movie.productionCompanies.isNotEmpty()) {
                        val productionCompanies = movie.productionCompanies.getProductionName()
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .padding(top = 16.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.production),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = FontFamily.SansSerif,
                                modifier = Modifier.padding(start = 4.dp)
                            )

                            Text(
                                text = productionCompanies,
                                fontSize = 16.sp,
                                textAlign = TextAlign.End,
                                fontFamily = FontFamily.SansSerif,
                                modifier = Modifier.padding(start = 16.dp)
                            )

                        }
                    }

                    if (movie.genres.isNotEmpty()) {
                        val movieCategory = movie.genres.getCategories()
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .padding(top = 16.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.category),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = FontFamily.SansSerif,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                            Text(
                                text = movieCategory,
                                fontSize = 16.sp,
                                fontFamily = FontFamily.SansSerif,
                                textAlign = TextAlign.End,
                                modifier = Modifier.padding(start = 16.dp)
                            )

                        }
                    }

                }
            }
        }
    }
}