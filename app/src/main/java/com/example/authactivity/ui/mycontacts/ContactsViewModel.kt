package com.example.authactivity.ui.mycontacts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import com.example.authactivity.model.ListData
import com.example.authactivity.repository.ContactsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactsViewModel (private val repository: ContactsRepository): BaseViewModel<BaseEvent>() {

    val data: MutableLiveData<MutableList<String>> = MutableLiveData()

    fun updateList(data: ListData) {
        repository.updateList(data)
    }

    fun insertList(data: ListData?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (data != null) {
                repository.insertList(data)
            }
        }
    }

    fun deleteList(data: ListData?) {
        if (data != null) {
            repository.deleteList(data)
        }
    }
}