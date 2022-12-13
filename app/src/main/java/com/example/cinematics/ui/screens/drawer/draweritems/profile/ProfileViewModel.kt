package com.example.cinematics.ui.screens.drawer.draweritems.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinematics.data.local.dao.UserDao
import com.example.cinematics.data.local.entities.toUserAuthItem
import com.example.cinematics.domain.model.auth.UserAuthItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val dao: UserDao) : ViewModel() {

    val user = MutableStateFlow<UserAuthItem>(UserAuthItem(0, "", ""))

    fun getUserData() {
        viewModelScope.launch {
            user.value = dao.getUser()?.toUserAuthItem()!!
        }
    }

    fun logoutUser() {
        viewModelScope.launch {
            dao.logoutUser()
        }
    }

}