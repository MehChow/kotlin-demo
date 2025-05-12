package com.example.demoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.demoapp.ui.screens.HomeScreen
import com.example.demoapp.ui.screens.welcome.WelcomeScreen

enum class Screen { Welcome, Home }

@Composable
fun AppNavGraph(navController: NavHostController, isFirstLaunch: Boolean) {
    NavHost(
        navController = navController,
        startDestination = if (isFirstLaunch) Screen.Welcome.name else Screen.Home.name
    ) {
        composable(Screen.Welcome.name) { WelcomeScreen(navController) }
        composable(Screen.Home.name) { HomeScreen() }
    }
}