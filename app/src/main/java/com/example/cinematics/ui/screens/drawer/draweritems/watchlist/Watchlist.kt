package com.example.cinematics.ui.screens.drawer.draweritems.watchlist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.cinematics.data.datasource.remote.ApiURL
import com.example.cinematics.data.local.entities.MovieDetailEntity
import com.example.cinematics.navigation.NavigationScreen
import com.example.cinematics.ui.screens.moviedetail.MovieDetailViewModel

@Composable
fun Watchlist(
    navController: NavController,
) {
    val viewModel = hiltViewModel<MovieDetailViewModel>()
    val moviesItem by viewModel.moviesState.collectAsState()
    viewModel.getAllMoviesFromDatabase()

    LazyVerticalGrid(columns = GridCells.Fixed(2),
        modifier = Modifier.padding(start = 5.dp, top = 5.dp, end = 5.dp),
        content = {
            items(moviesItem.size) { count ->
                MovieDetailItemView(moviesItem[count], navController, viewModel)
            }
        })
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieDetailItemView(
    item: MovieDetailEntity,
    navController: NavController,
    viewModel: MovieDetailViewModel,
) {

    val openDialog = remember { mutableStateOf(false) }

    if (openDialog.value) {
        AlertDialog(onDismissRequest = {
            openDialog.value = false
        }, title = {
            Text(text = "Do you want to delete item from database?", textAlign = TextAlign.Center)
        }, confirmButton = {
            Button(onClick = {
                viewModel.deleteItem(item)
                openDialog.value = false
                viewModel.getAllMoviesFromDatabase()
            }) {
                Text(text = "Confirm", fontSize = 18.sp, color = Color.White)
            }
        }, dismissButton = {
            Button(onClick = { openDialog.value = false }) {
                Text(text = "Dismiss", fontSize = 18.sp, color = Color.White)
            }
        })
    }

    Column(modifier = Modifier.padding(7.5.dp)) {
        Image(painter = rememberAsyncImagePainter(ApiURL.IMAGE_URL.plus(item.poster_path)),
            contentDescription = "movieItemView",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(250.dp)
                .clip(MaterialTheme.shapes.large)
                .combinedClickable(onClick = {
                    navController.navigate(NavigationScreen.MovieDetail.MOVIE_DETAIL.plus("/${item.id}"))
                }, onLongClick = {
                    openDialog.value = true
                }))
    }
}