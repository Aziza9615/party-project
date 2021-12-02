package com.example.authactivity.ui.mycontacts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import com.example.authactivity.model.ListData
import com.example.authactivity.repository.ContactsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactsViewModel(private val repository: ContactsRepositoryImpl) : BaseViewModel<BaseEvent>() {

    val data: MutableLiveData<MutableList<ListData>> = MutableLiveData()
   // val message: MutableLiveData<String> = MutableLiveData()
    var list: MutableList<ListData>? = mutableListOf()
    var filteredProducts: MutableList<ListData> = mutableListOf()

    init {
        subscribeToData()
        subscribeToMessage()
        getList()
    }

    fun getList() {
        repository.getList()
    }

    fun insertList(data: ListData?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (data != null) {
                repository.insertList(data)
            }
        }
    }

    fun restoreList(data: ListData?) {
        if (data != null) {
            repository.restoreList(data)
        }
    }

    fun updateList(data: ListData) {
        repository.updateList(data)
    }

    fun deleteList(data: ListData?) {
        if (data != null) {
            repository.deleteList(data)
        }
    }

    private fun subscribeToData() {
        repository.data.observeForever {
            data.value = it
            list = data.value
            filteredProducts = it
        }
    }

    private fun subscribeToMessage() {
        repository.message?.observeForever {
            message?.value = it
        }
    }
}