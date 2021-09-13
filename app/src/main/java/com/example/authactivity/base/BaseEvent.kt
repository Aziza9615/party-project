package com.example.authactivity.base

sealed class BaseEvent {
    class Error(message: String) : BaseEvent()
    class Success<T>(result: T) : BaseEvent()
    class Loading(state: Boolean) : BaseEvent()
}

sealed class AuthEvent: BaseEvent() {
    object SuccessSignUp : AuthEvent()
    object ErrorSignUp : AuthEvent()
}

sealed class RegistrationEvent : BaseEvent() {
    object SuccessSignUp : RegistrationEvent()
    object ErrorSignUp : RegistrationEvent()
}
