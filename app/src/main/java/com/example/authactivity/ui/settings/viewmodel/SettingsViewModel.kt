package com.example.authactivity.ui.settings.viewmodel

import com.example.authactivity.base.*
import com.example.authactivity.repository.SettingsRepository

class SettingsViewModel(private val repository: SettingsRepository) : BaseViewModel<BaseEvent>() {

    init {
        Sett()
        Delete()
    }

    fun Sett () {
        loading.value = true
        disposable.add(
                repository.fetchSettings()
                        .doOnTerminate { loading.value = false }
                        .subscribe(
                                { event.value = SettingsEvent.SettingFetched(it) },
                                { message.value = it.message }
                        )
        )
    }

    fun Delete () {
        loading.value = true
        disposable.add(
                repository.fetchDelete()
                        .doOnTerminate { loading.value = false }
                        .subscribe(
                                { event.value = DeleteEvent.DeleteFetched(it) },
                                { message.value = it.message }
                        )
        )
    }
}