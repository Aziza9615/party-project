package com.example.authactivity.repository

import androidx.lifecycle.MutableLiveData
import com.example.authactivity.database.ListDao
import com.example.authactivity.model.AcceptData
import com.example.authactivity.model.ContactData

interface AcceptRepository{
    fun getAccept()
    fun insertAccept(data: AcceptData)
    fun updateAccept(data: AcceptData)
    fun restoreAccept(data: AcceptData)
    fun deleteAccept(data: AcceptData)
}

class AcceptRepositoryImpl(private val database: ListDao): AcceptRepository {//TODO КРЕШИТ ИЗ-ЗА ТОГО ЧТО НЕ ДОБАВИЛИ ListDao в DI!!!! МЫ ЭТО ПРОХОДИЛИ!!!

    val data: MutableLiveData<MutableList<AcceptData>> = MutableLiveData()
    val message: MutableLiveData<String> = MutableLiveData()

    override fun getAccept() {
        data.value = database.getAccept()
    }

    override fun insertAccept(data: AcceptData) {
        database.insertAccept(data)
    }

    override fun updateAccept(data: AcceptData) {
        database.updateAccept(data)
    }

    override fun restoreAccept(data: AcceptData) {
        database.restoreAccept(data)
    }

    override fun deleteAccept(data: AcceptData) {
        database.deleteAccept(data)
    }
}