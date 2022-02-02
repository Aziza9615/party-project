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

//    fun createContact(name: String, category: String, amount: Double) {
//        loading.value = true
//        disposable.add(
//            repository.createContact(name = name, category = category, amount = amount)
//                .doOnTerminate { loading.value = false }
//                .subscribe(
//                    { event.value = ContactEvent.ContactCreated(it) },
//                    { message.value = it.message }
//                )
//        )
//    }

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
            data.value = it
            contact = data.value
            filteredContact = it
        }
    }

    fun subscribeToMessage() {
        repository.message?.observeForever {
            message.value = it
        }
    }
}