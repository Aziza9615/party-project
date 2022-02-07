package com.example.authactivity.di

import androidx.room.Room
import com.example.authactivity.database.AppDatabase
import com.example.authactivity.database.DATABASE_NAME
import com.example.authactivity.local.PrefsHelper
import com.example.authactivity.repository.*
import com.example.authactivity.ui.lang.LangViewModel
import com.example.authactivity.ui.mycontacts.ContactViewModel
import com.example.authactivity.ui.mycontacts.ContactsFragment
import com.example.authactivity.ui.mycontacts.bottomSheet.ListViewModel
import com.example.authactivity.ui.mycontacts.category.CategoryViewModel
import com.example.authactivity.ui.onBoard.OnBoardViewModel
import com.example.authactivity.ui.setting.LangSettingsFragment
import com.example.authactivity.ui.setting.SettingsFragment
import com.example.authactivity.ui.statistics.StatisticsFragment
import com.example.authactivity.ui.tablayout.fragment.AcceptFragment
import com.example.authactivity.ui.tablayout.fragment.GiveFragment
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val fragmentModule = module {
    fragment { SettingsFragment() }
    fragment { LangSettingsFragment() }
    fragment { StatisticsFragment() }
    fragment { ContactsFragment() }
    fragment { AcceptFragment() }
    fragment { GiveFragment() }
}

val viewModelModule = module {
    viewModel { ListViewModel(get()) }
    viewModel { OnBoardViewModel() }
    viewModel { LangViewModel() }
    viewModel { CategoryViewModel(get()) }
    viewModel { ContactViewModel(get()) }
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
    factory { ListRepositoryImpl(get()) }
    factory { CategoryRepositoryImpl (get()) }
    factory { ContactRepositoryImpl(get()) }
    single { PrefsHelper(androidContext()) }
}

