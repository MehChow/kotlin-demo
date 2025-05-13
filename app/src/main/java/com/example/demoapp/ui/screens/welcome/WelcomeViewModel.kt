package com.example.demoapp.ui.screens.welcome

import android.content.Context
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.data.DataStoreHelper
import com.example.demoapp.utils.BiometricAuthManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.fragment.app.FragmentActivity

class WelcomeViewModel(
    private val biometricAuthManager: BiometricAuthManager = BiometricAuthManager()
) : ViewModel() {

    private val _biometricState = MutableStateFlow<BiometricState>(BiometricState.Idle)
    val biometricState: StateFlow<BiometricState> = _biometricState.asStateFlow()

    fun checkBiometricAndProceed(context: Context) {
        val biometricManager = BiometricManager.from(context)
        when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                _biometricState.value = BiometricState.TriggerPrompt
            }
            else -> {
                Toast.makeText(
                    context,
                    "Please set up biometric authentication in your device settings.",
                    Toast.LENGTH_LONG
                ).show()
                _biometricState.value = BiometricState.Idle
            }
        }
    }

    fun authenticate(activity: FragmentActivity) {
        biometricAuthManager.authenticate(
            activity = activity,
            onSuccess = {
                viewModelScope.launch {
                    DataStoreHelper.setFirstLaunch(activity, false)
                    _biometricState.value = BiometricState.Success
                }
            },
            onErrorOrCancel = {
                _biometricState.value = BiometricState.Idle
            }
        )
    }
}

sealed class BiometricState {
    data object Idle : BiometricState()
    data object TriggerPrompt : BiometricState()
    data object Success : BiometricState()
}