package com.example.authactivity.di

import androidx.room.Room
import com.example.authactivity.database.AppDatabase
import com.example.authactivity.database.DATABASE_NAME
import com.example.authactivity.local.PrefsHelper
import com.example.authactivity.repository.ContactsRepository
import com.example.authactivity.repository.ContactsRepositoryImpl
import com.example.authactivity.ui.lang.LangViewModel
import com.example.authactivity.ui.mycontacts.ContactsFragment
import com.example.authactivity.ui.mycontacts.ContactsViewModel
import com.example.authactivity.ui.onBoard.OnBoardViewModel
import com.example.authactivity.ui.setting.SettingsFragment
import com.example.authactivity.ui.statistics.StatisticsFragment
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val fragmentModule = module {
    fragment { SettingsFragment() }
    fragment { StatisticsFragment() }
    fragment { ContactsFragment() }
}

val viewModelModule = module {
    viewModel { ContactsViewModel(get()) }
    viewModel { OnBoardViewModel() }
    viewModel { LangViewModel() }
}

val repositoryModule = module {
    factory<ContactsRepository> { ContactsRepositoryImpl(get()) }
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