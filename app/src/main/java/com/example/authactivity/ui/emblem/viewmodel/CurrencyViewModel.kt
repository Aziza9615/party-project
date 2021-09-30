package com.example.authactivity.ui.emblem.viewmodel

import com.example.authactivity.base.AmountEvent
import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import com.example.authactivity.repository.CurrencyRepository

class CurrencyViewModel(private val repository: CurrencyRepository): BaseViewModel<BaseEvent>() {

    init {
        CreateAdd( )
    }

    fun CreateAdd () {
        loading.value = true
        disposable.add(
                repository.amount()
                        .doOnTerminate { loading.value = false }
                        .subscribe(
                                { event.value = AmountEvent.AmoutFetched(it) },
                                { message.value = it.message }
                        )
        )
    }
}