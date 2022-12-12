package com.example.cinematics.ui.screens.bottombar.nowplaying

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cinematics.ui.components.HomeScreen

@Composable
fun NowPlaying(
    navController: NavController
){
    val nowPlayingViewModel = hiltViewModel<NowPlayingViewModel>()
    HomeScreen(navController = navController, movies = nowPlayingViewModel.popularMovies)
}