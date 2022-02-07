package com.example.authactivity.ui.mycontacts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import com.example.authactivity.model.ContactData
import com.example.authactivity.repository.ContactRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel(private val repository: ContactRepositoryImpl) : BaseViewModel<BaseEvent>() {

    val data: MutableLiveData<MutableList<ContactData>> = MutableLiveData()
    val message: MutableLiveData<String> = MutableLiveData()
    var contact: MutableList<ContactData>? = mutableListOf()
    var filteredContact: MutableList<ContactData> = mutableListOf()

    fun updateContact(data: ContactData) {
        repository.updateContact(data)
    }

    fun getContact() {
        repository.getContact()
    }

    fun insertContact(data: ContactData) {
        viewModelScope.launch(Dispatchers.IO) {
            data?.let { repository.insertContact(data) }
        }
    }

    fun deleteContact() {
        repository.deleteContact(contact)
    }

    fun restoreProduct(data: ContactData?) {
        if (data != null) {
            repository.restoreContact(data)
        }
    }

    fun subscribeToData() {
        repository.data.observeForever {
            it?.let { result -> data.value = result}
            data.value?.let { data -> contact = data }
            data.value = it
        }
    }

    fun subscribeToMessage() {
        repository.message?.observeForever {
            message.value = it
        }
    }
}