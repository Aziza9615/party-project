package com.example.authactivity.api

import com.example.authactivity.AppContacts.GET_PARTY
import com.example.authactivity.model.User
import io.reactivex.Observable
import retrofit2.http.GET

interface PartyApi {
    @GET(GET_PARTY)
    fun fetchUserProfile(): Observable<User>
}