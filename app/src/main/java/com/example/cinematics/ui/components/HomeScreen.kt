package com.example.cinematics.ui.components

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import com.example.cinematics.data.datasource.remote.ApiURL
import com.example.cinematics.data.model.MovieItem
import com.example.cinematics.navigation.NavigationScreen
import com.example.cinematics.navigation.currentRoute
import com.example.cinematics.utils.PagingLoadingState
import com.example.cinematics.R
import com.example.cinematics.ui.theme.White
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(
    navController: NavController,
    movies: Flow<PagingData<MovieItem>>,
) {
    val activity = (LocalContext.current as? Activity)
    val progressBar = remember {
        mutableStateOf(false)
    }
    val openDialog = remember {
        mutableStateOf(false)
    }
    val moviesItem: LazyPagingItems<MovieItem> = movies.collectAsLazyPagingItems()
    BackHandler(enabled = (currentRoute(navController) === NavigationScreen.HOME)) {
        //login screen
        openDialog.value = true
    }
    Column {
        Column {
            CircularIndeterminateProgressBar(isDisplayed = progressBar.value, verticalBias = 0.4f)
            LazyColumn {
                items(moviesItem.itemCount) { counter ->
                    val item = moviesItem[counter]
                    Row(modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth()
                        .background(White)
                        .padding(horizontal = 12.dp)
                        .clickable {
                            navController.navigate(NavigationScreen.MovieDetail.MOVIE_DETAIL.plus("/${item?.id}"))
                        },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start) {

                        ImageItem(title = item?.title,
                            modifier = Modifier
                                .height(160.dp)
                                .wrapContentWidth(),
                            url = item?.posterPath)

                        Spacer(modifier = Modifier.width(12.dp))
                        Column(verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.Start) {
                            Text(text = item?.title.toString(),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold)
                            Text(text = item?.releaseDate.toString(), fontSize = 20.sp)
                            Row(modifier = Modifier.wrapContentSize(),
                                verticalAlignment = Alignment.CenterVertically) {
                                Image(painterResource(id = R.drawable.ic_star_24),
                                    contentDescription = null,
                                    modifier = Modifier.wrapContentSize())
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = item?.voteAverage.toString(),
                                    fontSize = 20.sp,
                                    color = Color.Black)
                            }
                        }
                    }
                    Divider(color = Color.Black, thickness = 1.dp)
                }
            }
        }
        if (openDialog.value) {
            ExitAlertDialog(navController, {
                openDialog.value = it
            }) {
                activity?.finish()
            }
        }
    }
    moviesItem.PagingLoadingState {
        progressBar.value = it
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImageItem(
    url: String?,
    modifier: Modifier = Modifier,
    title: String? = "Title",
) {
    Image(painter = rememberAsyncImagePainter(ApiURL.IMAGE_URL.plus(url)),
        contentDescription = title,
        modifier = modifier)
}

@Composable
fun MovieItemView(item: MovieItem, navController: NavController) {
    Column(modifier = Modifier.padding(7.5.dp)) {
        Image(painter = rememberAsyncImagePainter(ApiURL.IMAGE_URL.plus(item.posterPath)),
            contentDescription = "movieItemView",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(250.dp)
                .clip(MaterialTheme.shapes.large)
                .clickable {
                    navController.navigate(NavigationScreen.MovieDetail.MOVIE_DETAIL.plus("/${item.id}"))
                })
    }
}