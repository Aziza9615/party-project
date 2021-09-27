package com.example.authactivity.di

import com.example.authactivity.local.PrefsHelper
import com.example.authactivity.network.*
import com.example.authactivity.repository.*
import com.example.authactivity.ui.activity.viewmodel.AmountViewModel
import com.example.authactivity.ui.bottom.HomeFragment
import com.example.authactivity.ui.bottom.ListFragment
import com.example.authactivity.ui.bottom.StatisticFragment
import com.example.authactivity.ui.main.MainViewModel
import com.example.authactivity.ui.draw.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val fragmentModule = module {
    fragment { ShareFragment() }
    fragment { EstimationFragment() }
    fragment { InvoicesFragment() }
    fragment { ConnectionFragment() }
    fragment { CurrencyFragment() }
    fragment { HomeFragment() }
    fragment { ListFragment() }
    fragment { StatisticFragment() }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { AmountViewModel(get()) }
    //viewModel { CurrencyViewModel(get()) }
}

val repositoryModule = module {
    factory<MainRepository> { MainRepositoryImpl(get()) }
    factory<AmountRepository> { AmountRepositoryImpl(get()) }
}

val networkRepository = module {
    single { provideRetrofit(get()) }
    single { provideOkHttpClient(get()) }
    single { provideHttpLoginingInterceptor() }
    single { provideHeadersInterceptor(get()) }
    single { PrefsHelper(androidContext()) }
    single { providePartyApi(get()) }
    single { provideAmountApi(get()) }
    //single { provideDrawApi(get()) }
}