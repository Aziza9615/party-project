package com.example.authactivity.ui.activity.viewmodel

import com.example.authactivity.base.AmountEvent
import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import com.example.authactivity.repository.AmountRepository
import com.example.authactivity.repository.UserRepository
import java.io.File

class AmountViewModel(private val repository: AmountRepository): BaseViewModel<BaseEvent>() {

    fun CreateAdd (amount: String, price: Double) {
        loading.value = true
        disposable.add(
                repository.amount(amount = amount, price = price)
                        .doOnTerminate { loading.value = false }
                        .subscribe(
                                { event.value = AmountEvent.AmoutFetched(it) },
                                { message.value = it.message }
                        )
        )
    }
}