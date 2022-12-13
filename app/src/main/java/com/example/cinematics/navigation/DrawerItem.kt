package com.example.cinematics.navigation

sealed class DrawerItem(val title: String, val route: String) {
    object Profile : DrawerItem("Profile", "profile")
//    object Settings : DrawerItem("Settings", "settings")
    object WatchList : DrawerItem("Watchlist", "preferences")
//    object Help : DrawerItem("Help", "help")
}