package com.example.authactivity.ui.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import com.example.authactivity.base.CategoryEvent
import com.example.authactivity.model.CategoryData
import com.example.authactivity.repository.CategoryRepository
import com.example.authactivity.repository.CategoryRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(private val repository: CategoryRepositoryImpl): BaseViewModel<BaseEvent>(){

    val data: MutableLiveData<MutableList<CategoryData>> = MutableLiveData()
    val message: MutableLiveData<String>? = MutableLiveData()
    var category: MutableList<CategoryData>? = mutableListOf()
    var filteredCategory: MutableList<CategoryData> = mutableListOf()

    init {
        subscribeToData()
        subscribeToMessage()
        getCategory()
    }

    fun getCategory() {
        repository.getCategory()
    }

    fun insertCategory(data: CategoryData?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (data != null) {
                repository.insertCategory(data)
            }
        }
    }

    fun restoreCategory(data: CategoryData?) {
        if (data != null) {
            repository.restoreCategory(data)
        }
    }

    fun updateCategory(data: CategoryData) {
        repository.updateCategory(data)
    }

    fun deleteCategory(data: CategoryData?) {
        if (data != null) {
            repository.deleteCategory(data)
        }
    }

    private fun subscribeToData() {
        repository.data.observeForever {
            data.value = it
            category = data.value
            filteredCategory = it
        }
    }

    private fun subscribeToMessage() {
        repository.message?.observeForever {
            message?.value = it
        }
    }
}