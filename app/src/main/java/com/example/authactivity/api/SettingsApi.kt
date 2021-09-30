package com.example.authactivity.api

import com.example.authactivity.AppContacts
import com.example.authactivity.model.CurrencyModel
import com.example.authactivity.model.SettingsModel
import io.reactivex.Observable
import retrofit2.http.GET

interface SettingsApi {
    @GET(AppContacts.SETT)
    fun fetchSettings(): Observable<SettingsModel>

    @GET(AppContacts.DELETE)
    fun fetchDelete(): Observable<SettingsModel>
}