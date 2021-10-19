package com.example.authactivity.api

import com.example.authactivity.AppContacts.GET_DELETE_LIST
import com.example.authactivity.AppContacts.GET_PARTY
import com.example.authactivity.model.ListData
import com.example.authactivity.model.Main
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PartyApi {
    @GET(GET_PARTY)
    fun fetchUser(): Observable<Main>

    @POST(GET_DELETE_LIST)
    fun deleteAlData(): Observable<ListData>
}