package com.neo.parking.feature.login.ui

sealed class Screen(val route : String) {
    object LOGINSCREEN: Screen("Login")
    object REGISTERSCREEN: Screen("Register")
    object FORGOT_PASSWORD: Screen("Forgot_password")
}