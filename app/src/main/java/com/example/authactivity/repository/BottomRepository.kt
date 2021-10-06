package com.example.authactivity.repository

import com.example.authactivity.api.BottomApi
import com.example.authactivity.api.CurrencyApi
import com.example.authactivity.model.BottomModel
import com.example.authactivity.model.CurrencyModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface BottomRepository {
    fun fetchList(): Observable<BottomModel>
}

class BottomRepositoryImpl(private val api: BottomApi) : BottomRepository {

    override fun fetchList(): Observable<BottomModel> {
        return api.fetchList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}