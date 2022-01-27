package com.example.authactivity.ui.mycontacts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import com.example.authactivity.model.CategoryData
import com.example.authactivity.model.ListData
import com.example.authactivity.repository.ListRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(private val repository: ListRepositoryImpl) : BaseViewModel<BaseEvent>() {

    val data: MutableLiveData<MutableList<ListData>> = MutableLiveData()
    val message: MutableLiveData<String> = MutableLiveData()
    var list: MutableList<ListData>? = mutableListOf()
    var filteredList: MutableList<ListData> = mutableListOf()

    fun updateList(data: ListData) {
        repository.updateList(data)
    }

    fun getList() {
        repository.getList()
    }

    fun insertList(data: ListData) {
        viewModelScope.launch(Dispatchers.IO) {
            data?.let { repository.insertList(data) }
        }
    }

    fun deleteList(data: ListData?) {
        if (data != null) {
            repository.deleteList(data)
        }
    }

    fun subscribeToData() {
        repository.data.observeForever {
            data.value = it
            list = data.value
            filteredList = it
        }
    }

    fun subscribeToMessage() {
        repository.message?.observeForever {
            message.value = it
        }
    }
}