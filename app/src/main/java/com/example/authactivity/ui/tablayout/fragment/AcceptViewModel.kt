package com.example.authactivity.ui.tablayout.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import com.example.authactivity.model.AcceptData
import com.example.authactivity.model.ContactData
import com.example.authactivity.repository.AcceptRepositoryImpl
import com.example.authactivity.repository.ContactRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AcceptViewModel(private val repository: AcceptRepositoryImpl) : BaseViewModel<BaseEvent>() {

    val data: MutableLiveData<MutableList<AcceptData>> = MutableLiveData()
    val message: MutableLiveData<String> = MutableLiveData()
    var contact: MutableList<AcceptData>? = mutableListOf()

    fun updateAccept(data: AcceptData) {
        repository.updateAccept(data)
    }

    fun getAccept() {
        repository.getAccept()
    }

    fun insertAccept(data: AcceptData) {
        viewModelScope.launch(Dispatchers.IO) {
            data?.let { repository.insertAccept(data) }
        }
    }

    fun deleteAccept(data: AcceptData?) {
        if (data != null) {
            repository.deleteAccept(data)
        }
    }
}