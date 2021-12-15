package com.example.authactivity.ui.mycontacts

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.authactivity.base.BaseViewHolder
import com.example.authactivity.databinding.ItemBottomSheetBinding
import com.example.authactivity.model.ListData

class ContactAdapter (private val listener: ClickListener): com.example.authactivity.base.BaseAdapter() {

    private var items = mutableListOf<ListData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = ItemBottomSheetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(
                binding
        )
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = items[position]
        val holder = holder as ContactViewHolder
        holder.bind(item)
        holder.itemView.setOnClickListener { listener.onClick(item) }
    }

    fun addItem(item: ListData) {
        items.add(item)
        notifyDataSetChanged()
    }
}

interface ClickListener {
    fun onClick(item: ListData)
}

class ContactViewHolder(var binding: ItemBottomSheetBinding) : BaseViewHolder(binding.root) {
    fun bind(item: ListData) {
    }
}