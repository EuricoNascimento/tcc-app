package com.github.edu.look.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.github.edu.look.repository.local.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext context: Context
): ViewModel() {
    private val sessionManager = SessionManager(context)

     fun signIn(idToken: String?): Boolean {
         return if (!idToken.isNullOrEmpty()) {
             sessionManager.saveAuthToken(idToken)
             true
         } else false
    }
}