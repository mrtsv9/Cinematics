package com.example.cinematics.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.example.cinematics.data.model.NavItem

val DrawerNavItems = listOf(
    NavItem(
        label = DrawerItem.Profile.title,
        icon = Icons.Filled.Person,
        route = DrawerItem.Profile.route
    ),
    NavItem(
        label = DrawerItem.Preferences.title,
        icon = Icons.Filled.Assistant,
        route = DrawerItem.Preferences.route
    )
)