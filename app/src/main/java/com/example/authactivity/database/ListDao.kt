package com.example.authactivity.database

import androidx.room.*
import com.example.authactivity.model.AlertData
import com.example.authactivity.model.CategoryData
import com.example.authactivity.model.ListData

@Dao
interface ListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(data: ListData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun restoreList(data: ListData)

    @Update
    fun updateItem(data: ListData)

    @Query("SELECT * FROM list")
    fun getList(): MutableList<ListData>

    @Delete
    fun deleteList(data: ListData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(data: CategoryData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun restoreCategory(data: CategoryData)

    @Update
    fun updateCategory(data: CategoryData)

    @Query("SELECT * FROM category")
    fun getCategory(): MutableList<CategoryData>

    @Delete
    fun deleteCategory(data: CategoryData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(data: AlertData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun restoreContact(data: AlertData)

    @Update
    fun updateContact(data: AlertData)

    @Query("SELECT * FROM alert")
    fun getContact(): MutableList<AlertData>

    @Delete
    fun deleteContact(data: AlertData)
}