package com.example.authactivity.repository

import com.example.authactivity.api.ThemeApi
import com.example.authactivity.model.ThemeModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface ThemeRepository {
    fun fetchTheme(): Observable<ThemeModel>
}

class ThemeRepositoryImpl(private val api: ThemeApi) : ThemeRepository {
    override fun fetchTheme(): Observable<ThemeModel> {
        return api.fetchTheme()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}