package com.example.authactivity.ui.bottom.list

import com.example.authactivity.base.*
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

    fun fetchEdit() {
        loading.value = true
        disposable.add(
            repository.fetchEdit()
                .doOnTerminate { loading.value = false }
                .subscribe(
                    { event.value = EditEvent.EditFetched(it) },
                    { message.value = it.message })
        )
    }
    fun fetchDetail() {
        loading.value = true
        disposable.add(
            repository.fetchDetail()
                .doOnTerminate { loading.value = false }
                .subscribe(
                    { event.value = DetailEvent.DetailFetched(it) },
                    { message.value = it.message })
        )
    }
}