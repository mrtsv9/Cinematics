package com.example.cinematics.ui.screens.bottombar.upcoming

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cinematics.ui.components.HomeScreen


@Composable
fun Upcoming(
    navController: NavController,
) {
    val upComingViewModel = hiltViewModel<UpComingViewModel>()
    HomeScreen(
        navController = navController,
        movies = upComingViewModel.upcomingMovies
    )
}