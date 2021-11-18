package com.example.authactivity.di

import com.example.authactivity.local.PrefsHelper
import com.example.authactivity.ui.lang.LangViewModel
import com.example.authactivity.ui.main.MainViewModel
import com.example.authactivity.ui.onBoard.OnBoard2ViewModel
import com.example.authactivity.ui.onBoard.OnBoard3ViewModel
import com.example.authactivity.ui.onBoard.OnBoardViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val fragmentModule = module {
}

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { LangViewModel() }
    viewModel { OnBoardViewModel() }
    viewModel { OnBoard2ViewModel() }
    viewModel { OnBoard3ViewModel() }
}

val repositoryModule = module {
}

val networkRepository = module {
    single { PrefsHelper(androidContext()) }
}