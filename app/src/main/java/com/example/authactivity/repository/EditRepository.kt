package com.example.authactivity.repository

import androidx.lifecycle.MutableLiveData
import com.example.authactivity.database.ListDao
import com.example.authactivity.model.EditData

interface EditRepository {
    fun insertEdit(data: EditData)
    fun getEdit()
    fun deleteEdit(list: MutableList<EditData>)
}

class EditRepositoryImpl(private val database: ListDao): EditRepository {

    val data: MutableLiveData<MutableList<EditData>> = MutableLiveData()
    val message: MutableLiveData<String>? = MutableLiveData()

    override fun insertEdit(data: EditData) {
        database.insertEdit(data)
    }

    override fun getEdit() {
        data.value = database.getEdit()
    }

    override fun deleteEdit(list: MutableList<EditData>) {
        database.deleteEdit(list)
    }
}