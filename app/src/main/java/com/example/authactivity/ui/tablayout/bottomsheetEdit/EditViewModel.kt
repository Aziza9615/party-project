package com.example.authactivity.ui.tablayout.bottomsheetEdit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import com.example.authactivity.model.ContactData
import com.example.authactivity.model.EditData
import com.example.authactivity.repository.ContactRepositoryImpl
import com.example.authactivity.repository.EditRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditViewModel(private val repository: EditRepositoryImpl) : BaseViewModel<BaseEvent>() {

    val data: MutableLiveData<MutableList<EditData>> = MutableLiveData()
    val message: MutableLiveData<String> = MutableLiveData()
    var edit: MutableList<EditData> = mutableListOf()

    fun updateEdit(data: EditData) {
        repository.updateEdit(data)
    }

    fun getEdit() {
        repository.getEdit()
    }

    fun insertEdit(data: EditData) {
        viewModelScope.launch(Dispatchers.IO) {
            data?.let { repository.insertEdit(data) }
        }
    }

    fun deleteEdit() {
        repository.deleteEdit(edit)
    }
}