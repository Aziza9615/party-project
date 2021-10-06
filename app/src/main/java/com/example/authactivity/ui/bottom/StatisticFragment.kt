package com.example.authactivity.ui.bottom

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentStatisticBinding
import com.example.authactivity.ui.bottom.list.ListViewModel

class StatisticFragment : BaseFragment<ListViewModel, FragmentStatisticBinding>(
    ListViewModel::class) {

    // private var publicationArray: MutableList<PsychologistId> = mutableListOf()

    override fun attachBinding(
        list: MutableList<FragmentStatisticBinding>,
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ) {
        list.add(FragmentStatisticBinding.inflate(layoutInflater, container, attachToRoot))
    }

    override fun setupViews() {
    }

    override fun subscribeToLiveData() {
    }
}