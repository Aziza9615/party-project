package com.example.authactivity.repository

import androidx.lifecycle.MutableLiveData
import com.example.authactivity.database.ListDao
import com.example.authactivity.model.ContactData

interface ContactRepository{
    fun getContact()
    fun insertContact(data: ContactData)
    fun updateContact(data: ContactData)
    fun restoreContact(data: ContactData)
    fun deleteContact(contact: MutableList<ContactData>?)
}

class ContactRepositoryImpl(private val database: ListDao): ContactRepository {//TODO КРЕШИТ ИЗ-ЗА ТОГО ЧТО НЕ ДОБАВИЛИ ListDao в DI!!!! МЫ ЭТО ПРОХОДИЛИ!!!

    val data: MutableLiveData<MutableList<ContactData>> = MutableLiveData()
    val message: MutableLiveData<String> = MutableLiveData()

    override fun getContact() {
        data.value = database.getContact()
    }

    override fun insertContact(data: ContactData) {
        database.insertContact(data)
    }

    override fun updateContact(data: ContactData) {
        database.updateContact(data)
    }

    override fun restoreContact(data: ContactData) {
        database.restoreContact(data)
    }

    override fun deleteContact(contact: MutableList<ContactData>?) {
        database.deleteContact(contact)
    }
}