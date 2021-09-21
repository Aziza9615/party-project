package com.example.authactivity.base

import com.example.authactivity.model.AmountModel
import com.example.authactivity.model.User

sealed class BaseEvent {
    class Error(message: String) : BaseEvent()
    class Success<T>(result: T) : BaseEvent()
    class Loading(state: Boolean) : BaseEvent()
}

sealed class UserEvent : BaseEvent() {
    class UserFetched(val array: User): UserEvent()
}

sealed class AmountEvent : BaseEvent() {
    class AmoutFetched(val item: AmountModel) : AmountEvent()
}

