package com.example.authactivity.api

import com.example.authactivity.AppContacts
import com.example.authactivity.model.ThemeModel
import io.reactivex.Observable
import retrofit2.http.GET

interface ThemeApi {
    @GET(AppContacts.GET_THEME)
    fun fetchTheme(): Observable<ThemeModel>
}