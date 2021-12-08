package com.example.authactivity.ui.currency

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import com.example.authactivity.model.LangData
import com.example.authactivity.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class  CurrencyViewModel (private val repository: CurrencyRepository): BaseViewModel<BaseEvent>() {

    val data: MutableLiveData<MutableList<String>> = MutableLiveData()

    init {
        getCurrency()
    }

    fun getCurrency() {
        repository.getCurrency()
    }

    fun updateCurrency(data: LangData) {
        repository.updateCurrency(data)
    }

    fun insertCurrency(data: LangData?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (data != null) {
                repository.insertCurrency(data)
            }
        }
    }

    fun deleteCurrency(data: LangData?) {
        if (data != null) {
            repository.deleteCurrency(data)
        }
    }
}