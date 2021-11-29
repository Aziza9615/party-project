package com.example.authactivity.database

import androidx.room.*
import com.example.authactivity.model.LangData

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
}