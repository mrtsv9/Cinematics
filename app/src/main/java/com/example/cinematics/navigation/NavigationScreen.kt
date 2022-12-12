package com.example.cinematics.navigation

object NavigationScreen {
    const val HOME = "home"
    const val LOGIN = "login"
    const val POPULAR = "popular"
    const val TOP_RATED = "toprated"
    const val UP_COMING = "upcoming"
    const val GENRE_ID = "genreId"
    const val NAVIGATION_GENRE = "navigation_genre_item"
    const val NAVIGATION_GENRE_WITH_GENRE_ID = "navigation_genre_item/{$GENRE_ID}"

    object MovieDetail {
        const val MOVIE_DETAIL = "movieDetail"
        const val MOVIE_ITEM = "movieItem"
        const val MOVIE_DETAIL_PATH = "/{movieItem}"
    }
    object ArtistDetail {
        const val ARTIST_DETAIL = "artistDetail"
        const val ARTIST_ID = "artistId"
        const val ARTIST_DETAIL_PATH = "/{artistId}"
    }
}