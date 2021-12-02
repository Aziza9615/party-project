package com.example.authactivity.database

import androidx.room.*
import com.example.authactivity.model.LangData
import com.example.authactivity.model.ListData

@Dao
interface ListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrency(data: LangData)

    @Update
    fun updateCurrency(data: LangData)

    @Query("SELECT * FROM lang")
    fun getCurrency(): MutableList<LangData>

    @Delete
    fun deleteCurrency(data: LangData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(data: ListData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun restoreList(data: ListData)

    @Update
    fun updateList(data: ListData)

    @Query("SELECT * FROM list")
    fun getList(): MutableList<ListData>

    @Delete
    fun deleteList(data: ListData)
}