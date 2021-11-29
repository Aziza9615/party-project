package com.example.authactivity.di

import androidx.room.Room
import com.example.authactivity.database.AppDatabase
import com.example.authactivity.database.DATABASE_NAME
import com.example.authactivity.local.PrefsHelper
import com.example.authactivity.repository.CurrencyRepository
import com.example.authactivity.repository.CurrencyRepositoryImpl
import com.example.authactivity.ui.currency.CurrencyViewModel
import com.example.authactivity.ui.lang.LangViewModel
import com.example.authactivity.ui.main.MainViewModel
import com.example.authactivity.ui.onBoard.OnBoardViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val fragmentModule = module {
}

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { OnBoardViewModel() }
    viewModel { LangViewModel() }
    viewModel { CurrencyViewModel(get()) }
}

val repositoryModule = module {
//    factory<LangRepository> { LangRepositoryImpl(get()) }
    factory<CurrencyRepository> { CurrencyRepositoryImpl(get()) }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            DATABASE_NAME
        )
            .allowMainThreadQueries()
//            .addMigrations(MIGRATION_1_2)
            .build()
    }
    single { get<AppDatabase>().listDao() }
}

val networkRepository = module {
    single { PrefsHelper(androidContext()) }
}