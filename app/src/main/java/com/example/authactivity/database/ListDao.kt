package com.example.authactivity.database

import androidx.room.*
import com.example.authactivity.model.ListData

@Dao
interface ListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(data: ListData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun restoreList(data: ListData)

    @Update
    fun updateList(data: ListData)

    @Delete
    fun deleteList(data: ListData)
}