package com.neo.parking.feature.login.vm

data class UiState(
    val password: String = "",
    val email: String = "",
    val rememberLogin: Boolean = false,
)

sealed class UiIntent {
    object DoLoginIntent : UiIntent()
    object CheckLoggedIntent : UiIntent()
    object UpdateRememberIntent : UiIntent()
    class EmailUpdateIntent(val email: String) : UiIntent()
    class PasswordUpdateIntent(val password: String) : UiIntent()

}

sealed class Event {
    object ONLOGGED : Event()
}