package com.example.authactivity.base

import com.example.authactivity.model.*

sealed class BaseEvent {
    class Error(message: String) : BaseEvent()
    class Success<T>(result: T) : BaseEvent()
    class Loading(state: Boolean) : BaseEvent()
}

sealed class UserEvent : BaseEvent() {
    class UserFetched(val array: User): UserEvent()
}

sealed class AmountEvent : BaseEvent() {
    class AmoutFetched(val item: CurrencyModel) : AmountEvent()
}

sealed class ThemeEvent : BaseEvent() {
    class ThemeFetched(val item: ThemeModel) : ThemeEvent()
}

sealed class SettingsEvent : BaseEvent() {
    class SettingFetched(val item: SettingsModel) : SettingsEvent()
}

sealed class DeleteEvent : BaseEvent() {
    class DeleteFetched(val item: SettingsModel) : DeleteEvent()
}

sealed class ListEvent : BaseEvent() {
    class ListFetched(val item: BottomModel) : ListEvent()
}

