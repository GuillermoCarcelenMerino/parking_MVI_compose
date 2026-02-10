package com.neo.parking.feature.parking.ui

sealed class Screen(val route : String) {
    object PARKINGS_LIST_SCREEN: Screen("Parkings_list")
}