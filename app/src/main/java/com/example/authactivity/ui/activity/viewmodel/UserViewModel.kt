package com.example.authactivity.ui.activity.viewmodel

import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import com.example.authactivity.base.UserEvent
import com.example.authactivity.repository.UserRepository

class UserViewModel(private val repository: UserRepository) : BaseViewModel<BaseEvent>() {

    init {
        fetchUser()
    }

    fun fetchUser() {
        loading.value = true
        disposable.add(
                repository.fetchUser()
                        .doOnTerminate { loading.value = false }
                        .subscribe(
                                { event.value = UserEvent.UserFetched(it) },
                                { message.value = it.message })
        )
    }
}