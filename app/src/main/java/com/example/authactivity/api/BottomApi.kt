package com.example.authactivity.api

import com.example.authactivity.AppContacts
import com.example.authactivity.model.ListData
import io.reactivex.Observable
import retrofit2.http.GET

interface BottomApi {
    @GET(AppContacts.GET_BOTTOM)
    fun fetchList(): Observable<ListData>
}