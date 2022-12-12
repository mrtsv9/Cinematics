package com.example.cinematics.ui.screens.bottombar.popular

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cinematics.ui.components.HomeScreen

@Composable
fun Popular(
    navController: NavController
) {
    val popularViewModel = hiltViewModel<PopularViewModel>()
    HomeScreen(navController, movies = popularViewModel.popularMovies)
}