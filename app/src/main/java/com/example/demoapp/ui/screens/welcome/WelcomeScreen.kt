package com.example.demoapp.ui.screens.welcome

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.demoapp.R
import com.example.demoapp.navigation.Routes
import com.example.demoapp.ui.components.AppImage
import com.example.demoapp.ui.components.BodyText
import com.example.demoapp.ui.components.PrimaryButton
import com.example.demoapp.ui.components.SecondaryButton
import com.example.demoapp.ui.components.TitleText

@Composable
fun WelcomeScreen(
    navController: NavController,
    viewModel: WelcomeViewModel = viewModel()
) {
    val context = LocalContext.current
    val activity = context as? FragmentActivity ?: return
    val biometricState by viewModel.biometricState.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current

    // Handle biometric authentication trigger
    DisposableEffect(lifecycleOwner, biometricState) {
        if (biometricState is BiometricState.TriggerPrompt) {
            viewModel.authenticate(activity)
        }
        onDispose {}
    }

    // Handle navigation on success
    LaunchedEffect(biometricState) {
        if (biometricState is BiometricState.Success) {
            navController.navigate(Routes.home) {
                popUpTo(Routes.welcome) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAEBD7)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AppImage(
            resourceId = R.drawable.sushang_icon,
            contentDescription = "Welcome icon"
        )
        TitleText(text = "Welcome!")
        BodyText(
            text = "This app securely stores your passwords. Biometric authentication is required for access."
        )
        Spacer(modifier = Modifier.height(40.dp))

        PrimaryButton(
            text = "Get started",
            onClick = {
                viewModel.checkBiometricAndProceed(context)
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        SecondaryButton(
            text = "Go to settings",
            onClick = {
                val intent = Intent(Settings.ACTION_SECURITY_SETTINGS)
                context.startActivity(intent)
            }
        )
    }
}