package com.example.authactivity.repository

import androidx.lifecycle.MutableLiveData
import com.example.authactivity.database.ListDao
import com.example.authactivity.model.CategoryData

interface CategoryRepository{
    fun getCategory()
    fun insertCategory(data: CategoryData)
    fun updateCategory(data: CategoryData)
    fun restoreCategory(data: CategoryData)
    fun deleteCategory(data: CategoryData)
}

class CategoryRepositoryImpl(private val database: ListDao): CategoryRepository {

    val data: MutableLiveData<MutableList<CategoryData>> = MutableLiveData()
    val message: MutableLiveData<String>? = MutableLiveData()

    override fun getCategory() {
        data.value = database.getCategory()
    }

    override fun insertCategory(data: CategoryData) {
        database.insertCategory(data)
    }

    override fun updateCategory(data: CategoryData) {
        database.updateCategory(data)
    }

    override fun restoreCategory(data: CategoryData) {
        database.restoreCategory(data)
    }

    override fun deleteCategory(data: CategoryData) {
        database.deleteCategory(data)
    }
}