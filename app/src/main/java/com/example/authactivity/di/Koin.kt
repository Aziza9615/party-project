package com.example.authactivity.di

import com.example.authactivity.local.PrefsHelper
import com.example.authactivity.network.*
import com.example.authactivity.repository.*
import com.example.authactivity.ui.emblem.viewmodel.CurrencyViewModel
import com.example.authactivity.ui.bottom.HomeFragment
import com.example.authactivity.ui.bottom.StatisticFragment
import com.example.authactivity.ui.bottom.list.ListViewModel
import com.example.authactivity.ui.main.MainViewModel
import com.example.authactivity.ui.draw.*
import com.example.authactivity.ui.emblem.amount.AmountFragment
import com.example.authactivity.ui.emblem.EmblemFragment
import com.example.authactivity.ui.settings.ThemeViewModel
import com.example.authactivity.ui.settings.viewmodel.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val fragmentModule = module {
    fragment { ShareFragment() }
    fragment { EstimationFragment() }
    fragment { ConnectionFragment() }
    fragment { CurrencyFragment() }
    fragment { HomeFragment() }
    fragment { StatisticFragment() }
    fragment { AmountFragment() }
    fragment { EmblemFragment() }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { CurrencyViewModel(get()) }
    viewModel { ThemeViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { ListViewModel(get()) }
}

val repositoryModule = module {
    factory<MainRepository> { MainRepositoryImpl(get()) }
    factory<CurrencyRepository> { CurrencyRepositoryImpl(get()) }
    factory<ThemeRepository> { ThemeRepositoryImpl(get()) }
    factory<SettingsRepository> { SettingsRepositoryImpl(get()) }
    factory<BottomRepository> { BottomRepositoryImpl(get()) }
}

val networkRepository = module {
    single { provideRetrofit(get()) }
    single { provideOkHttpClient(get()) }
    single { provideHttpLoginingInterceptor() }
    single { provideHeadersInterceptor(get()) }
    single { PrefsHelper(androidContext()) }
    single { providePartyApi(get()) }
    single { provideCurrencyApi(get()) }
    single { provideThemeApi(get()) }
    single { provideSettingsApi(get()) }
    single { provideBottomApi(get()) }
}