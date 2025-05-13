package com.example.demoapp.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.example.demoapp.navigation.Routes
import com.example.demoapp.utils.BiometricAuthManager

@Composable
fun AuthScreen(navController: NavController) {
    val context = LocalContext.current
    val activity = context as? FragmentActivity ?: return
    val lifecycleOwner = LocalLifecycleOwner.current
    val biometricAuthManager = BiometricAuthManager()

    DisposableEffect(lifecycleOwner) {
        biometricAuthManager.authenticate(
            activity = activity,
            onSuccess = {
                navController.navigate(Routes.home) {
                    popUpTo(Routes.auth) { inclusive = true }
                }
            },
            onErrorOrCancel = {
                activity.finishAffinity() // Close the app
            }
        )
        onDispose {}
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {
        // Empty or add a simple loading indicator (see UX recommendations)
    }
}