package com.example.authactivity.ui.bottom

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentListBinding
import com.example.authactivity.ui.bottom.viewmodel.ListViewModel

class ListFragment : BaseFragment<ListViewModel,FragmentListBinding>(
    ListViewModel::class) {

   // private var publicationArray: MutableList<PsychologistId> = mutableListOf()

    override fun attachBinding(
        list: MutableList<FragmentListBinding>,
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ) {
        list.add(FragmentListBinding.inflate(layoutInflater, container, attachToRoot))
    }

    override fun setupViews() {
        TODO("Not yet implemented")
    }

    override fun subscribeToLiveData() {
        TODO("Not yet implemented")
    }
}
