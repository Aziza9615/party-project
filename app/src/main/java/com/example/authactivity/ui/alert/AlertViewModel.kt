package com.example.authactivity.ui.alert

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import com.example.authactivity.model.AlertData
import com.example.authactivity.model.ListData
import com.example.authactivity.repository.AlertRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlertViewModel(private val repository: AlertRepositoryImpl) : BaseViewModel<BaseEvent>() {

    val data: MutableLiveData<MutableList<AlertData>> = MutableLiveData()
    val message: MutableLiveData<String> = MutableLiveData()
    var contact: MutableList<AlertData>? = mutableListOf()

    fun updateContact(data: AlertData) {
        repository.updateContact(data)
    }

    fun getContact() {
        repository.getContact()
    }

    fun insertContact(data: AlertData?) {
        viewModelScope.launch(Dispatchers.IO) {
            data?.let { repository.insertContact(data) }
        }
    }

    fun deleteContact(data: AlertData?) {
        if (data != null) {
            repository.deleteContact(data)
        }
    }
}