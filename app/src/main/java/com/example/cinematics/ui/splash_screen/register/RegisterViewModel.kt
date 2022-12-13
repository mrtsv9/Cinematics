package com.example.cinematics.ui.splash_screen.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinematics.domain.model.auth.UserAuthItem
import com.example.cinematics.domain.use_case.auth.RegisterUseCase
import com.example.photosnetwork.domain.model.auth.UserAuthInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase): ViewModel() {

    private val _userAuthItem = Channel<UserAuthItem>()
    val userAuthData = _userAuthItem.receiveAsFlow()

    private val _isErrorOccurred = MutableSharedFlow<Boolean>()
    val isErrorOccurred = _isErrorOccurred.asSharedFlow()

    fun registerUser(userAuthInput: UserAuthInput) {
        viewModelScope.launch {
            val userAuthData = registerUseCase(userAuthInput)
            if (userAuthData == null) _isErrorOccurred.emit(true)
            else _userAuthItem.send(userAuthData)
        }
    }

}