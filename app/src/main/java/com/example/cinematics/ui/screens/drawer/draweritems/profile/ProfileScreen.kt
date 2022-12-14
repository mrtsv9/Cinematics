package com.example.cinematics.ui.screens.drawer.draweritems.profile

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cinematics.R
import com.example.cinematics.ui.splash_screen.SplashActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {

    val viewModel = hiltViewModel<ProfileViewModel>()
    val user = viewModel.user.collectAsState()
    viewModel.getUserData()
    val context = LocalContext.current
    val isAppBarVisible = remember {
        mutableStateOf(true)
    }
    Column(
        modifier = Modifier.fillMaxHeight().fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Login: ", fontSize = 24.sp, color = Color.Black, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "${user.value?.login}", fontSize = 24.sp, color = Color.Black, textAlign = TextAlign.Center)
        }
        Button(modifier = Modifier.padding(10.dp), onClick = {
            viewModel.logoutUser()
            val intent = Intent(context, SplashActivity::class.java)
            context.startActivity(intent)
        }) {
            Text(stringResource(R.string.log_out))
        }
    }
}