package com.neo.parking.feature.parking.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun ParkingNavHost() {
    val rememberNavHostController = rememberNavController()
    NavHost(
        rememberNavHostController,
        startDestination = Screen.PARKINGS_LIST_SCREEN.route,
        modifier = Modifier.fillMaxSize()
    ) {

        composable(route = Screen.PARKINGS_LIST_SCREEN.route) {
            ParkingsScrenRoute()
        }

    }
}