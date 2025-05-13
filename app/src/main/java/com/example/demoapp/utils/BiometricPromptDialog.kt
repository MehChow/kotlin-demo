package com.example.demoapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun BiometricPromptDialog(
    activity: FragmentActivity,
    biometricAuthManager: BiometricAuthManager,
    onAuthSuccess: () -> Unit,
    onAuthErrorOrCancel: () -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        biometricAuthManager.authenticate(
            activity = activity,
            onSuccess = onAuthSuccess,
            onErrorOrCancel = onAuthErrorOrCancel
        )
        onDispose {
            // Optional: Cleanup if needed (e.g., cancel prompt if possible)
        }
    }
}