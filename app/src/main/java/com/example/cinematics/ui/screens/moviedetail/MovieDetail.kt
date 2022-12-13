package com.example.cinematics.ui.screens.moviedetail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.cinematics.R
import com.example.cinematics.data.datasource.remote.ApiURL
import com.example.cinematics.data.model.BaseModel
import com.example.cinematics.data.model.MovieItem
import com.example.cinematics.data.model.artist.Artist
import com.example.cinematics.data.model.artist.Cast
import com.example.cinematics.data.model.moviedetail.MovieDetail
import com.example.cinematics.navigation.NavigationScreen
import com.example.cinematics.ui.components.CircularIndeterminateProgressBar
import com.example.cinematics.ui.theme.ColorOrange
import com.example.cinematics.ui.theme.LightGrey
import com.example.cinematics.utils.network.DataState
import com.example.cinematics.utils.pagingLoadingState

@Composable
fun MovieDetail(navController: NavController, movieId: Int) {
    val movieDetailViewModel = hiltViewModel<MovieDetailViewModel>()
    val progressBar = remember {
        mutableStateOf(false)
    }
    val movieDetail = movieDetailViewModel.movieDetail
    val recommendedMovie = movieDetailViewModel.recommendedMovie
    val artist = movieDetailViewModel.artist

    val colorAdd = remember { mutableStateOf(ColorOrange) }
    val imageAdd = remember { mutableStateOf(R.drawable.ic_add_24) }
    val textAdd = remember { mutableStateOf("Add to Watchlist") }


//    val interactorSource = remember { MutableInteractionSource() }
//    val isAddedToWatchList by interactorSource.collectIsPressedAsState()
//    val color = if (isAddedToWatchList) LightGrey else ColorOrange

    LaunchedEffect(key1 = true) {
        movieDetailViewModel.movieDetailApi(movieId)
        movieDetailViewModel.recommendedMovieApi(movieId, page = 1)
        movieDetailViewModel.movieCredit(movieId)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        CircularIndeterminateProgressBar(isDisplayed = progressBar.value, verticalBias = 0.4f)
        movieDetail.value?.let { it ->
            if (it is DataState.Success<MovieDetail>) {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Image(painter = rememberAsyncImagePainter(ApiURL.IMAGE_URL.plus(it.data.backdrop_path)),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
//                            .fillMaxSize()
                            .height(250.dp)
                            .padding(start = 5.dp, end = 5.dp)
                            .clip(MaterialTheme.shapes.large))
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 15.dp, end = 15.dp)) {
                        Text(text = it.data.title,
                            modifier = Modifier.padding(top = 10.dp),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 2,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold)
                        Row(modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 10.dp, top = 10.dp)) {
                            Column(Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = stringResource(R.string.language),
                                    fontWeight = FontWeight.Bold

                                )
                                Text(text = it.data.original_language, fontWeight = FontWeight.Bold)
                            }
                            Column(Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = stringResource(R.string.rating),
                                    fontWeight = FontWeight.Bold)

                                Text(text = it.data.vote_average.toString(),
                                    fontWeight = FontWeight.Bold)
                            }
                            Column(Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = stringResource(R.string.release_date),
                                    fontWeight = FontWeight.Bold

                                )
                                Text(text = it.data.release_date, fontWeight = FontWeight.Bold)
                            }
                        }
                        Text(text = stringResource(R.string.description),
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge)
                        Text(text = it.data.overview, modifier = Modifier.padding(bottom = 10.dp))
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(colorAdd.value)
                            .clickable {
                                colorAdd.value = LightGrey
                                imageAdd.value = R.drawable.ic_check_24
                                textAdd.value = "Added to Watchlist!"
                            },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start) {
                            Image(painter = painterResource(imageAdd.value),
                                contentDescription = "Icon add",
                                Modifier.padding(start = 8.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = textAdd.value, fontSize = 20.sp, color = Color.Black)
                        }
                        recommendedMovie.value?.let {
                            if (it is DataState.Success<BaseModel>) {
                                RecommendedMovie(navController, it.data.results)
                            }
                        }
                        artist.value?.let {
                            if (it is DataState.Success<Artist>) {
                                ArtistAndCrew(navController, it.data.cast)
                            }
                        }
                    }

                }
            }
        }
        recommendedMovie.pagingLoadingState {
            progressBar.value = it
        }
        movieDetail.pagingLoadingState {
            progressBar.value = it
        }
    }
}

@Composable
fun RecommendedMovie(navController: NavController?, recommendedMovie: List<MovieItem>) {
    Column(modifier = Modifier.padding(bottom = 10.dp)) {
        Text(text = stringResource(R.string.similar),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge)
        LazyRow {
            items(recommendedMovie, itemContent = { item ->
                Image(painter = rememberAsyncImagePainter(ApiURL.IMAGE_URL.plus(item.posterPath)),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(240.dp)
                        .width(180.dp)
                        .padding(end = 10.dp, top = 5.dp, bottom = 5.dp)
                        .clip(shape = MaterialTheme.shapes.large)
                        .clickable {
                            navController?.navigate(NavigationScreen.MovieDetail.MOVIE_DETAIL.plus("/${item.id}"))
                        }

                )
            })
        }
    }
}

@Composable
fun ArtistAndCrew(navController: NavController?, cast: List<Cast>) {
    Column(modifier = Modifier.padding(bottom = 10.dp)) {
        Text(text = stringResource(R.string.cast),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge)
        LazyRow {
            items(cast, itemContent = { item ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painter = rememberAsyncImagePainter(ApiURL.IMAGE_URL.plus(item.profilePath)),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(240.dp)
                            .width(180.dp)
                            .padding(end = 10.dp, top = 5.dp, bottom = 5.dp)
                            .clip(shape = MaterialTheme.shapes.large)
                            .clickable {
                                navController?.navigate(NavigationScreen.ArtistDetail.ARTIST_DETAIL.plus(
                                    "/${item.id}"))
                            })
                    Text(text = item.name,
                        modifier = Modifier.padding(end = 10.dp, top = 5.dp, bottom = 5.dp))
                }

            })
        }
    }
}

