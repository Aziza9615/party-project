package com.example.authactivity.repository

import androidx.lifecycle.MutableLiveData
import com.example.authactivity.database.ListDao
import com.example.authactivity.model.ContactData
import com.example.authactivity.model.EditData
import com.example.authactivity.ui.tablayout.TabActivity

interface EditRepository{
    fun getEdit()
    fun insertEdit(data: EditData)
    fun updateEdit(data: EditData)
    fun restoreEdit(data: EditData)
    fun deleteEdit(list: MutableList<EditData>)
}

class EditRepositoryImpl(private val database: ListDao): EditRepository {

    val data: MutableLiveData<MutableList<EditData>> = MutableLiveData()
    val message: MutableLiveData<String> = MutableLiveData()

    override fun getEdit() {
        data.value = database.getEdit()
    }

    override fun insertEdit(data: EditData) {
        database.insertEdit(data)
    }

    override fun updateEdit(data: EditData) {
        database.updateEdit(data)
    }

    override fun restoreEdit(data: EditData) {
        database.restoreEdit(data)
    }

    override fun deleteEdit(list: MutableList<EditData>) {
        database.deleteEdit(list)
    }
}