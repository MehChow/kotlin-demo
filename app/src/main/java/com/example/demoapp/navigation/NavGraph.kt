package com.example.demoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.demoapp.ui.screens.HomeScreen
import com.example.demoapp.ui.screens.auth.AuthScreen
import com.example.demoapp.ui.screens.welcome.WelcomeScreen

@Composable
fun AppNavGraph(navController: NavHostController, isFirstLaunch: Boolean) {
    NavHost(
        navController = navController,
        // First time use, display Welcome screen for onboarding
        startDestination = if (isFirstLaunch) Routes.welcome else Routes.auth
    ) {
        composable(Routes.welcome) { WelcomeScreen(navController) }
        composable(Routes.auth) { AuthScreen(navController) }
        composable(Routes.home) { HomeScreen() }
    }
}