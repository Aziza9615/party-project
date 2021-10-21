package com.example.authactivity.api

import com.example.authactivity.AppContacts
import com.example.authactivity.model.ListData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST

interface BottomApi {
    @POST(AppContacts.GET_BOTTOM)
    fun fetchList(): Observable<ListData>

    @POST(AppContacts.GET_EDIT)
    fun fetchEdit(): Observable<ListData>

    @POST(AppContacts.GET_DETAIL)
    fun fetchDetail(): Observable<ListData>
}