package com.example.authactivity.ui.bottom.list

import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import com.example.authactivity.base.ListEvent
import com.example.authactivity.model.ListData
import com.example.authactivity.repository.BottomRepository

class ListViewModel(private val repository: BottomRepository) : BaseViewModel<BaseEvent>() {

    var list: MutableList<ListData> = mutableListOf()

    init {
        fetchList()
    }

    fun fetchList() {
        loading.value = true
        disposable.add(
                repository.fetchList()
                        .doOnTerminate { loading.value = false }
                        .subscribe(
                                { event.value = ListEvent.ListFetched(it) },
                                { message.value = it.message })
        )
    }
}