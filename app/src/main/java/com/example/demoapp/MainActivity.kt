package com.example.demoapp

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.rememberNavController
import com.example.demoapp.data.DataStoreHelper
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.demoapp.navigation.AppNavGraph
import java.util.concurrent.atomic.AtomicBoolean

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // 1. Install splash screen
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 2. Track loading state
        val isDataLoaded = AtomicBoolean(false)

        // 3. Keep splash screen until data is loaded
        splashScreen.setKeepOnScreenCondition { !isDataLoaded.get() }

        setContent {
            // 4. Use null as initial value to indicate "not loaded"
                val isFirstLaunch by DataStoreHelper.isFirstLaunch(this).collectAsState(initial = null)
                val navController = rememberNavController()

                // 5. When loaded, update the flag to hide splash
                if (isFirstLaunch != null) {
                    isDataLoaded.set(true)
                AppNavGraph(navController, isFirstLaunch!!)
            }
        }
    }
}