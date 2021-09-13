package com.example.authactivity.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<EVENT : BaseEvent> : ViewModel() {

    var message = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val data = MutableLiveData<String>()
    val event = MutableLiveData<EVENT>()
}