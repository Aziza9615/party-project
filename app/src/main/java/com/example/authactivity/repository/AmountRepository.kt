package com.example.authactivity.repository

import com.example.authactivity.api.AmountApi
import com.example.authactivity.model.AmountModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody


interface AmountRepository {
    fun amount(amount: String,price: Double): Observable<AmountModel>
}

class AmountRepositoryImpl(private val api: AmountApi) : AmountRepository {

    override fun amount(amount: String, price: Double): Observable<AmountModel> {
        return api.amount(amount = amount, price = price)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}