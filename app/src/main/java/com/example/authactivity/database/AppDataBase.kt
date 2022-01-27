package com.example.authactivity.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.authactivity.model.CategoryData
import com.example.authactivity.model.ContactData
import com.example.authactivity.model.ListData

const val DATABASE_NAME = "kowum4a_app"

@Database(entities = [ListData::class, CategoryData::class, ContactData::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun listDao() : ListDao
}