package com.example.authactivity.ui.tablayout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import com.example.authactivity.model.EditData
import com.example.authactivity.repository.ContactRepositoryImpl
import com.example.authactivity.repository.EditRepositoryImpl

class EditViewModel(private val repository: EditRepositoryImpl) : BaseViewModel<BaseEvent>()  {

    val data: MutableLiveData<MutableList<EditData>> = MutableLiveData()
    val message: MutableLiveData<String>? = MutableLiveData()
    var edit: MutableList<EditData> = mutableListOf()

    init {
        getEdit()
    }

    fun getEdit() {
        repository.getEdit()
    }

    fun insertEdit(data: EditData) {
        repository.insertEdit(data)
    }

    fun deleteEdit() {
        repository.deleteEdit(edit)
    }
}