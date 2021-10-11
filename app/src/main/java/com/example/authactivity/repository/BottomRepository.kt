package com.example.authactivity.repository

import com.example.authactivity.api.BottomApi
import com.example.authactivity.model.ListData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface BottomRepository {
    fun fetchList(): Observable<ListData>
}

class BottomRepositoryImpl(private val api: BottomApi) : BottomRepository {

    override fun fetchList(): Observable<ListData> {
        return api.fetchList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}