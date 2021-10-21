package com.example.authactivity.repository

import com.example.authactivity.api.BottomApi
import com.example.authactivity.model.ListData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface BottomRepository {
    fun fetchList(): Observable<ListData>
    fun fetchEdit(): Observable<ListData>
    fun fetchDetail(): Observable<ListData>
}

class BottomRepositoryImpl(private val api: BottomApi) : BottomRepository {

    override fun fetchList(): Observable<ListData> {
        return api.fetchList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    override fun fetchEdit(): Observable<ListData> {
        return api.fetchEdit()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    override fun fetchDetail(): Observable<ListData> {
        return api.fetchDetail()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}