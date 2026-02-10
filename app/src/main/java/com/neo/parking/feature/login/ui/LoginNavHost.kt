package com.neo.parking.feature.login.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.neo.parking.feature.Register.ui.RegisterRouteScreen

@Composable
fun LoginNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.LOGINSCREEN.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Screen.LOGINSCREEN.route) {
            loginScreen(
                navigateForgotPassword = {
                    navController.navigate(Screen.FORGOT_PASSWORD.route)
                },
                navigateRegister = {
                    navController.navigate(
                        Screen.REGISTERSCREEN.route
                    )
                }
            )
        }
        composable(Screen.REGISTERSCREEN.route) {
            RegisterRouteScreen(onBack = { navController.popBackStack() })
        }
    }
}