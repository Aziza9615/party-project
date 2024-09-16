package com.example.authactivity.ui.mycontacts.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import com.example.authactivity.model.CategoryData
import com.example.authactivity.repository.CategoryRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(private val repository: CategoryRepositoryImpl) : BaseViewModel<BaseEvent>() {

    val data: MutableLiveData<MutableList<CategoryData>> = MutableLiveData()

    val message: MutableLiveData<String> = MutableLiveData()
    var category: MutableList<CategoryData>? = mutableListOf()
    var filteredCategory: MutableList<CategoryData> = mutableListOf()

    fun getCategory() {
        repository.getCategory()
    }

    fun insertCategory(data: CategoryData?) {
        viewModelScope.launch(Dispatchers.IO) {
            data?.let { repository.insertCategory(data) }
        }
    }

    fun subscribeToData() {
        repository.data.observeForever {
            data.value = it
            category = data.value
            filteredCategory = it
        }
    }

    fun subscribeToMessage() {
        repository.message?.observeForever {
            message.value = it
        }
    }
}