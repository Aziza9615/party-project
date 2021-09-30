package com.example.authactivity.repository

import com.example.authactivity.api.SettingsApi
import com.example.authactivity.model.SettingsModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface SettingsRepository {
    fun fetchSettings(): Observable<SettingsModel>
    fun fetchDelete(): Observable<SettingsModel>
}

class SettingsRepositoryImpl(private val api: SettingsApi) : SettingsRepository {

    override fun fetchSettings(): Observable<SettingsModel> {
        return api.fetchSettings()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun fetchDelete(): Observable<SettingsModel> {
        return api.fetchDelete()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}