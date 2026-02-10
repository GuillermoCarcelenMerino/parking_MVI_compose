package com.neo.parking.feature.Register.vm


sealed class UIIntent {
    object CreateAccountIntent : UIIntent()
    class UpdateMailIntent(val mail: String) : UIIntent()
    class UpdateNameIntent(val name: String) : UIIntent()
    class UpdateMailConfirmationIntent(val mail: String) : UIIntent()
    class UpdatePasswordIntent(val pass: String) : UIIntent()
}

data class UIState(
    val name: String = "",
    val mail: String = "",
    val mailConfirmation: String = "",
    val pass: String = ""
)

sealed class Event {
    object UserCreated : Event()
}