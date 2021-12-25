package com.example.authactivity.ui.mycontacts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import com.example.authactivity.model.ListData
import com.example.authactivity.repository.ContactsRepository
import com.example.authactivity.repository.ContactsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactsViewModel(private val contactsRepositoryImpl: ContactsRepositoryImpl): BaseViewModel<BaseEvent>() {

    val data: MutableLiveData<MutableList<ListData>> = MutableLiveData()

    //val message: MutableLiveData<String>? = MutableLiveData()
    var list: MutableList<ListData>? = mutableListOf()
    var filteredList: MutableList<ListData> = mutableListOf()

    init {
        subscribeToData()
        subscribeToMessage()
        getList()
    }

    fun updateList(data: ListData) {
        contactsRepositoryImpl.updateList(data)
    }

    fun getList() {
        contactsRepositoryImpl.getList()
    }

    fun insertList(data: ListData?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (data != null) {
                contactsRepositoryImpl.insertList(data)
            }
        }
    }

    fun deleteList(data: ListData?) {
        if (data != null) {
            contactsRepositoryImpl.deleteList(data)
        }
    }

    private fun subscribeToData() {
        contactsRepositoryImpl.data.observeForever {
            data.value = it
            list = data.value
            filteredList = it
        }
    }

    private fun subscribeToMessage() {
        contactsRepositoryImpl.message?.observeForever {
            message.value = it
        }
    }
}