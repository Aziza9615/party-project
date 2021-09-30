package com.example.authactivity.api

import com.example.authactivity.AppContacts
import com.example.authactivity.model.CurrencyModel
import com.example.authactivity.model.User
import io.reactivex.Observable
import retrofit2.http.GET

interface CurrencyApi {
    @GET(AppContacts.Currency_ADD)
    fun amount(): Observable<CurrencyModel>
}
