package com.example.cinematics.ui.components.appbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinematics.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(
//    title: () -> Unit,
    openDrawer: () -> Unit,
    openFilters: () -> Unit,
    onSearchClick: () -> Unit,
) {
    SmallTopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.ic_camera),
                contentDescription = null,
                modifier = Modifier.height(35.dp),
            )
            Text(text = stringResource(id = R.string.app_name),
                fontSize = 24.sp,
                modifier = Modifier.padding(start = 40.dp))
        },
        actions = {
            IconButton(
                onClick = { onSearchClick() },
            ) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                openDrawer()
            }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
            }
        }
    )
}