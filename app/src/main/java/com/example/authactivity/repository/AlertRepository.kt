package com.example.authactivity.repository

import androidx.lifecycle.MutableLiveData
import com.example.authactivity.database.ListDao
import com.example.authactivity.model.AlertData
import com.example.authactivity.model.ListData

interface AlertRepository{
    fun getContact()
    fun insertContact(data: AlertData)
    fun updateContact(data: AlertData)
    fun restoreContact(data: AlertData)
    fun deleteContact(data: AlertData)
}

class AlertRepositoryImpl(private val database: ListDao): AlertRepository {

    val data: MutableLiveData<MutableList<AlertData>> = MutableLiveData()
    val message: MutableLiveData<String> = MutableLiveData()

    override fun getContact() {
        data.value = database.getContact()
    }

    override fun insertContact(data: AlertData) {
        database.insertContact(data)
    }

    override fun updateContact(data: AlertData) {
        database.updateContact(data)
    }

    override fun restoreContact(data: AlertData) {
        database.restoreContact(data)
    }

    override fun deleteContact(data: AlertData) {
        database.deleteContact(data)
    }
}