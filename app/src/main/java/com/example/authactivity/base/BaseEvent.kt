package com.example.authactivity.base

import com.example.authactivity.model.*

sealed class BaseEvent {
    class Error(message: String) : BaseEvent()
    class Success<T>(result: T) : BaseEvent()
    class Loading(state: Boolean) : BaseEvent()
}


