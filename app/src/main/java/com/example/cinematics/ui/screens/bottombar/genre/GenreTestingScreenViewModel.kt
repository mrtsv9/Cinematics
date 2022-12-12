package com.example.cinematics.ui.screens.bottombar.genre

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinematics.data.model.Genres
import com.example.cinematics.data.repository.MovieRepository
import com.example.cinematics.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreTestingScreenViewModel @Inject constructor(private val repo: MovieRepository) :
    ViewModel() {
    val genres: MutableState<DataState<Genres>?> = mutableStateOf(null)

    fun genreListTestingScreen() {
        viewModelScope.launch {
            repo.genreList().onEach {
                genres.value = it
            }.launchIn(viewModelScope)
        }
    }
}