package com.example.authactivity.repository

import com.example.authactivity.api.PartyApi
import com.example.authactivity.model.Main
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface MainRepository {
    fun fetchUser(): Observable<Main>
}

class MainRepositoryImpl(private val api: PartyApi) : MainRepository {
    override fun fetchUser(): Observable<Main> {
        return api.fetchUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}