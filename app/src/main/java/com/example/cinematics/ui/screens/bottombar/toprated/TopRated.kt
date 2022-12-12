package com.example.cinematics.ui.screens.bottombar.toprated

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cinematics.ui.components.HomeScreen

@Composable
fun TopRated(
    navController: NavController
) {
    val topRatedViewModel = hiltViewModel<TopRatedViewModel>()
    HomeScreen(navController, movies = topRatedViewModel.topRatedMovies)
}