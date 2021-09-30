package com.example.authactivity.ui.settings

import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import com.example.authactivity.base.ThemeEvent
import com.example.authactivity.repository.ThemeRepository

class ThemeViewModel(private val repository: ThemeRepository): BaseViewModel<BaseEvent>() {

     init {
         fetchTheme()
     }

    fun fetchTheme() {
        loading.value = true
        disposable.add(
                repository.fetchTheme()
                        .doOnTerminate { loading.value = false }
                        .subscribe(
                                { event.value = ThemeEvent.ThemeFetched(it) },
                                { message.value = it.message })
        )
    }
}