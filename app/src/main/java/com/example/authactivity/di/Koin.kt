package com.example.authactivity.di

import androidx.room.Room
import com.example.authactivity.database.AppDatabase
import com.example.authactivity.database.DATABASE_NAME
import com.example.authactivity.local.PrefsHelper
import com.example.authactivity.repository.*
import com.example.authactivity.ui.alert.AlertViewModel
import com.example.authactivity.ui.category.CategoryViewModel
import com.example.authactivity.ui.lang.LangViewModel
import com.example.authactivity.ui.mycontacts.ContactsFragment
import com.example.authactivity.ui.mycontacts.ContactsViewModel
import com.example.authactivity.ui.onBoard.OnBoardViewModel
import com.example.authactivity.ui.setting.LangSettingsFragment
import com.example.authactivity.ui.setting.SettingsFragment
import com.example.authactivity.ui.statistics.StatisticsFragment
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers.single
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val fragmentModule = module {
    fragment { SettingsFragment() }
    fragment { LangSettingsFragment() }
    fragment { StatisticsFragment() }
    fragment { ContactsFragment() }
}

val viewModelModule = module {
    viewModel { ContactsViewModel(get()) }
    viewModel { OnBoardViewModel() }
    viewModel { LangViewModel() }
    viewModel { CategoryViewModel(get()) }
    viewModel { AlertViewModel(get()) }
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
    factory { AlertRepositoryImpl (get()) }
    single { PrefsHelper(androidContext()) }
}

