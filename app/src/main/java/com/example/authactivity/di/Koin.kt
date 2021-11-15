package com.example.authactivity.di

import com.example.authactivity.local.PrefsHelper
import com.example.authactivity.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val fragmentModule = module {
}

val viewModelModule = module {
    viewModel { MainViewModel() }
}

val repositoryModule = module {
}

val networkRepository = module {
    single { PrefsHelper(androidContext()) }
}