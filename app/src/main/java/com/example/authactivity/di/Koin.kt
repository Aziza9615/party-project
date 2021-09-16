package com.example.authactivity.di

import com.example.authactivity.local.PrefsHelper
import com.example.authactivity.network.*
import com.example.authactivity.ui.list.ListFragment
import com.example.authactivity.ui.main.MainViewModel
import com.example.authactivity.ui.statistics.StatisticsFragment
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val fragmentModule = module {
    fragment { ListFragment() }
    fragment { StatisticsFragment() }
}

val viewModelModule = module {
    viewModel { MainViewModel() }
}

val repositoryModule = module {
    //factory<AuthRepository> { AuthRepositoryImpl(get(), get()) }
}

val networkRepository = module {
    single { provideRetrofit(get()) }
    single { provideOkHttpClient(get()) }
    single { provideHttpLoginingInterceptor() }
    single { provideHeadersInterceptor(get()) }
    single { PrefsHelper(androidContext()) }
    single { providePartyApi(get()) }
}