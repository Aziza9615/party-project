package com.example.authactivity.repository

import com.example.authactivity.model.AmountModel
import io.reactivex.Observable
import okhttp3.MultipartBody


interface AmountRepository {
    fun amount(amount: String,price: Double): Observable<AmountModel>
}

class AmountRepositoryImpl {
}