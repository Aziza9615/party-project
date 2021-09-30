package com.example.authactivity.repository

import com.example.authactivity.api.CurrencyApi
import com.example.authactivity.model.CurrencyModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


interface CurrencyRepository {
    fun amount(): Observable<CurrencyModel>
}

class CurrencyRepositoryImpl(private val api: CurrencyApi) : CurrencyRepository {
    override fun amount(): Observable<CurrencyModel> {
        return api.amount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}