package com.example.authactivity.api

import com.example.authactivity.AppContacts
import com.example.authactivity.model.CurrencyModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST

interface CurrencyApi {
    @GET(AppContacts.Currency_ADD)
    fun amount(): Observable<CurrencyModel>

    @POST(AppContacts.EMBLEM_ADD)
    fun emblem(): Observable<CurrencyModel>
}


