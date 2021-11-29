package com.example.authactivity.repository

import androidx.lifecycle.MutableLiveData
import com.example.authactivity.database.ListDao
import com.example.authactivity.model.LangData

interface CurrencyRepository{
    fun getCurrency()
    fun updateCurrency(data: LangData)
    fun insertCurrency(data: LangData)
    fun deleteCurrency(data: LangData)
}

class CurrencyRepositoryImpl(private val database: ListDao): CurrencyRepository {

    val data: MutableLiveData<MutableList<LangData>> = MutableLiveData()
    val message: MutableLiveData<String>? = MutableLiveData()

    override fun getCurrency() {
        data.value = database.getCurrency()
    }

    override fun updateCurrency(data: LangData) {
        database.updateCurrency(data)
    }

    override fun insertCurrency(data: LangData) {
        database.insertCurrency(data)
    }

    override fun deleteCurrency(data: LangData) {
        database.deleteCurrency(data)
    }

}