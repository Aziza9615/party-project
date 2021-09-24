package com.example.authactivity.api

import com.example.authactivity.AppContacts.AMOUNT_ADD
import com.example.authactivity.model.AmountModel
import io.reactivex.Observable
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface AmountApi {
    @Multipart
    @POST(AMOUNT_ADD)
    fun amount(@Part("amount") amount: String,
                     @Part("price") price: Double): Observable<AmountModel>
}
