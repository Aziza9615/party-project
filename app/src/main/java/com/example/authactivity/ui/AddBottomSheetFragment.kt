package com.example.authactivity.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.authactivity.base.BaseAddBottomSheetFragment
import com.example.authactivity.databinding.LayoutAddBottomSheetBinding


class AddBottomSheetFragment() : BaseAddBottomSheetFragment() {

    lateinit var binding: LayoutAddBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutAddBottomSheetBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun setupViews() {
        setupListener()
    }

    private fun setupListener() {
        binding.toolbar.setNavigationOnClickListener { this.onDestroyView() }
        //binding..setOnClickListener {  }
    }
}

