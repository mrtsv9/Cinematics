package com.example.cinematics.ui.screens.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cinematics.R
import com.example.cinematics.navigation.DrawerNavItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerCopy(
    navController: NavController,
    onDestinationClicked: (route: String) -> Unit,
) {
    val items = DrawerNavItems
    val selectedItem = remember {
        mutableStateOf(items[0])
    }
    Column {
        Column(
            modifier = Modifier
                .padding(horizontal = 34.dp)
                .padding(top = 40.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_camera),
                contentDescription = null
            )
        }
        LazyColumn(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            items(items = items) { item ->
                NavigationDrawerItem(
                    icon = { Icon(item.icon, contentDescription = null) },
                    label = { Text(item.label) },
                    selected = item == selectedItem.value,
                    onClick = {
                        onDestinationClicked(item.route)
                        selectedItem.value = item
                    },
                    modifier = Modifier
                        .padding(NavigationDrawerItemDefaults.ItemPadding)
                        .padding(vertical = 8.dp)
                )
            }
        }
    }
}