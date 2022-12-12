package com.example.cinematics.ui.screens.bottombar.genre

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cinematics.ui.components.HomeScreen

@Composable
fun GenreScreen(
    navController: NavController,
    genreId: String
) {
    val genreViewModel = hiltViewModel<GenreViewModel>()
    HomeScreen(
        navController = navController,
        movies = genreViewModel.moviesByGenre(genreId)
    )
}