package com.example.authactivity.ui.main

import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import com.example.authactivity.base.DeleteAlertEvent
import com.example.authactivity.base.UserEvent
import com.example.authactivity.model.ListData
import com.example.authactivity.repository.MainRepository

class MainViewModel(private val repository: MainRepository) : BaseViewModel<BaseEvent>() {

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
    fun deleteAlData() {
        loading.value = true
        disposable.add(
            repository.deleteAlData()
                .doOnComplete { loading.value = false }
                .subscribe(
                    { event.value = DeleteAlertEvent.DeleteAlertFetched(it) },
                    { message.value = it.message })
        )
    }

}