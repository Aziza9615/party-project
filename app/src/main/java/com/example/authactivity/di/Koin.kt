package com.example.authactivity.di

import com.example.authactivity.local.PrefsHelper
import com.example.authactivity.network.*
import com.example.authactivity.repository.UserRepository
import com.example.authactivity.repository.UserRepositoryImpl
import com.example.authactivity.ui.activity.UserViewModel
import com.example.authactivity.ui.list.ListFragment
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
    viewModel { UserViewModel(get()) }
}

val repositoryModule = module {
    factory<UserRepository> { UserRepositoryImpl(get()) }
}

val networkRepository = module {
    single { provideRetrofit(get()) }
    single { provideOkHttpClient(get()) }
    single { provideHttpLoginingInterceptor() }
    single { provideHeadersInterceptor(get()) }
    single { PrefsHelper(androidContext()) }
    single { providePartyApi(get()) }
}