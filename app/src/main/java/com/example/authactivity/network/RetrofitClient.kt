package com.example.authactivity.network

import com.example.authactivity.AppContacts.BASE_URL
import com.example.authactivity.api.*
import com.example.authactivity.local.PrefsHelper
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun provideOkHttpClient(
    headersInterceptor: HeadersInterceptor
): OkHttpClient {
    return OkHttpClient()
        .newBuilder()
        .addInterceptor(headersInterceptor)
        .build()
}

fun provideHttpLoginingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

fun providePartyApi(retrofit: Retrofit) = retrofit.create(PartyApi::class.java)
fun provideCurrencyApi(retrofit: Retrofit) = retrofit.create(CurrencyApi::class.java)
fun provideThemeApi(retrofit: Retrofit) = retrofit.create(ThemeApi::class.java)
fun provideSettingsApi(retrofit: Retrofit) = retrofit.create(SettingsApi::class.java)
fun provideBottomApi(retrofit: Retrofit) = retrofit.create(BottomApi::class.java)

fun provideHeadersInterceptor(preferences: PrefsHelper)
        = HeadersInterceptor(preferences)

class HeadersInterceptor(private val preferences: PrefsHelper) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = preferences.getToken()
        val request = chain.request().newBuilder()
        if (token.isNotEmpty())
            request.addHeader("Authorization", "Token $token")

        return chain.proceed(request.build())
    }
}

fun provideAuthWithOutAuthenticatorApi(): PartyApi {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(provideHttpLoginingInterceptor()).build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PartyApi::class.java)
}

