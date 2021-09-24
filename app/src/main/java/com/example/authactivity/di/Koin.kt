package com.example.authactivity.di

import com.example.authactivity.local.PrefsHelper
import com.example.authactivity.network.*
import com.example.authactivity.repository.AmountRepository
import com.example.authactivity.repository.AmountRepositoryImpl
import com.example.authactivity.repository.UserRepository
import com.example.authactivity.repository.UserRepositoryImpl
import com.example.authactivity.ui.activity.viewmodel.AmountViewModel
import com.example.authactivity.ui.activity.viewmodel.UserViewModel
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
}

val viewModelModule = module {
    viewModel { UserViewModel(get()) }
    viewModel { AmountViewModel(get()) }
    //viewModel { CurrencyViewModel(get()) }
}

val repositoryModule = module {
    factory<UserRepository> { UserRepositoryImpl(get()) }
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