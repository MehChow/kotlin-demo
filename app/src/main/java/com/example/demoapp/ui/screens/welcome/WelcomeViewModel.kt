package com.example.demoapp.ui.screens.welcome

import android.content.Context
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.data.DataStoreHelper
import kotlinx.coroutines.launch

class WelcomeViewModel : ViewModel() {
    fun checkBiometricAndProceed(
        context: Context,
        onNavigateToHome: () -> Unit,
        onShowSettings: () -> Unit
    ) {
        val biometricManager = BiometricManager.from(context)
        when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                // Trigger biometric prompt (handled in UI)
                onNavigateToHome()
            }

            else -> {
                Toast.makeText(
                    context,
                    "Please set up biometric authentication in your device settings.",
                    Toast.LENGTH_LONG
                ).show()
                onShowSettings()
            }
        }
    }

    // Content for biometric checks, DataStoreHelper for persistence
    fun handleAuthSuccess(context: Context, onNavigateToHome: () -> Unit) {
        // Asynchronous operations use viewModelScope
        viewModelScope.launch {
            DataStoreHelper.setFirstLaunch(context, false)
            onNavigateToHome()
        }
    }
}