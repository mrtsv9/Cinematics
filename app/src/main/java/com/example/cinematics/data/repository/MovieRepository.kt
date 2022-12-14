package com.example.cinematics.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.cinematics.data.datasource.remote.paging.*
import com.example.cinematics.data.datasource.remote.ApiService
import com.example.cinematics.data.local.dao.MovieDetailDao
import com.example.cinematics.data.local.dao.TokenDao
import com.example.cinematics.data.local.dao.UserDao
import com.example.cinematics.data.local.entities.MovieDetailEntity
import com.example.cinematics.data.model.BaseModel
import com.example.cinematics.data.model.Genres
import com.example.cinematics.data.model.artist.Artist
import com.example.cinematics.data.model.artist.ArtistDetail
import com.example.cinematics.data.model.moviedetail.MovieDetail
import com.example.cinematics.data.model.moviedetail.toMovieDetailEntity
import com.example.cinematics.utils.network.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService,
    private val dao: MovieDetailDao,
    private val userDao: UserDao,
    private val tokenDao: TokenDao
) {

    suspend fun insertMovie(movie: MovieDetail) {
        val token = tokenDao.getToken()?.token!!
        val movieDetail = movie.toMovieDetailEntity()
        movieDetail.userToken = token
        dao.save(movieDetail)
    }

    suspend fun updateUserRating(userRating: Int, title: String) {
        val token = tokenDao.getToken()?.token!!
        dao.updateUserRating(userRating, title, token)
    }

    suspend fun isPresentByTitle(title: String): Boolean {
        val token = tokenDao.getToken()?.token!!
        val result = dao.isPresentByTitle(title, token)
        return result != null
    }

    suspend fun getAllSavedMovies(): List<MovieDetailEntity> {
        val token = tokenDao.getToken()?.token!!
        return dao.getAll(token)
    }

    suspend fun deleteItemFromDatabase(item: MovieDetail) {
        val token = tokenDao.getToken()?.token!!
        dao.deleteItem(item.title, token)
    }

    suspend fun deleteItemFromDatabase(item: MovieDetailEntity) {
        val token = tokenDao.getToken()?.token!!
        dao.deleteItem(item.title, token)
    }

    suspend fun getUserRating(title: String): Int? {
        val token = tokenDao.getToken()?.token!!
        return dao.getUserRating(title, token)
    }

    suspend fun movieList(page: Int): Flow<DataState<BaseModel>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.nowPlayingMovieList(page)
            emit(DataState.Success(searchResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun movieDetail(movieId: Int): Flow<DataState<MovieDetail>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.movieDetail(movieId)
            emit(DataState.Success(searchResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun recommendedMovie(movieId: Int, page: Int): Flow<DataState<BaseModel>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.recommendedMovie(movieId, page)
            emit(DataState.Success(searchResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }


    suspend fun search(searchKey: String): Flow<DataState<BaseModel>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.search(searchKey)
            emit(DataState.Success(searchResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun genreList(): Flow<DataState<Genres>> = flow {
        emit(DataState.Loading)
        try {
            val genreResult = apiService.genreList()
            emit(DataState.Success(genreResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun moviesByGenreId(page: Int, genreId: String): Flow<DataState<BaseModel>> = flow {
        emit(DataState.Loading)
        try {
            val genreResult = apiService.moviesByGenre(page, genreId)
            emit(DataState.Success(genreResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun movieCredit(movieId: Int): Flow<DataState<Artist>> = flow {
        emit(DataState.Loading)
        try {
            val artistResult = apiService.movieCredit(movieId)
            emit(DataState.Success(artistResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun artistDetail(personId: Int): Flow<DataState<ArtistDetail>> = flow {
        emit(DataState.Loading)
        try {
            val artistDetailResult = apiService.artistDetail(personId)
            emit(DataState.Success(artistDetailResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    fun nowPlayingPagingDataSource() = Pager(
        pagingSourceFactory = { NowPlayingPagingDataSource(apiService) },
        config = PagingConfig(pageSize = 1)
    ).flow

    fun popularPagingDataSource() = Pager(
        pagingSourceFactory = { PopularPagingDataSource(apiService) },
        config = PagingConfig(pageSize = 1)
    ).flow

    fun topRatedPagingDataSource() = Pager(
        pagingSourceFactory = { TopRatedPagingDataSource(apiService) },
        config = PagingConfig(pageSize = 1)
    ).flow

    fun upcomingPagingDataSource() = Pager(
        pagingSourceFactory = { UpcomingPagingDataSource(apiService) },
        config = PagingConfig(pageSize = 1)
    ).flow

    fun genrePagingDataSource(genreId: String) = Pager(
        pagingSourceFactory = { GenrePagingDataSource(apiService, genreId) },
        config = PagingConfig(pageSize = 1)
    ).flow
}