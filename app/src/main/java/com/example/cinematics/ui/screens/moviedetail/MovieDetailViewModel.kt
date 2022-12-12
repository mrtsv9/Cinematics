package com.example.cinematics.ui.screens.moviedetail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinematics.data.model.BaseModel
import com.example.cinematics.data.model.artist.Artist
import com.example.cinematics.data.model.moviedetail.MovieDetail
import com.example.cinematics.data.repository.MovieRepository
import com.example.cinematics.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val repository: MovieRepository) :
    ViewModel() {
    val movieDetail: MutableState<DataState<MovieDetail>?> = mutableStateOf(null)
    val recommendedMovie: MutableState<DataState<BaseModel>?> = mutableStateOf(null)
    val artist: MutableState<DataState<Artist>?> = mutableStateOf(null)

    //detail movie
    fun movieDetailApi(movieId: Int) {
        viewModelScope.launch {
            repository.movieDetail(movieId).onEach {
                movieDetail.value = it
            }.launchIn(viewModelScope)
        }
    }

    //similar movie
    fun recommendedMovieApi(movieId: Int, page: Int) {
        viewModelScope.launch {
            repository.recommendedMovie(movieId, page).onEach {
                recommendedMovie.value = it
            }.launchIn(viewModelScope)
        }
    }

    //artist movie
    fun movieCredit(movieId: Int) {
        viewModelScope.launch {
            repository.movieCredit(movieId).onEach {
                artist.value = it
            }.launchIn(viewModelScope)
        }
    }
}