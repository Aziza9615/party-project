package com.example.authactivity.ui.emblem

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.authactivity.base.BaseAdapter
import com.example.authactivity.base.BaseViewHolder
import com.example.authactivity.databinding.ItemAmountBinding
import com.example.authactivity.model.CurrencyModel

class AmountAdapter(private val listener: AmountClickListener) : BaseAdapter() {

    private var amountArray = mutableListOf<CurrencyModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding =
            ItemAmountBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AmountViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return amountArray.count()
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = amountArray[position]
        val holder = holder as AmountViewHolder
        holder.bind(item)
        holder.itemView.setOnClickListener { listener.onAmountClick(item) }
    }

    fun addItems(item: MutableList<CurrencyModel>) {
        amountArray = item
        notifyDataSetChanged()
    }
}

interface AmountClickListener {
    fun onAmountClick(item: CurrencyModel)
}

class AmountViewHolder(var binding: ItemAmountBinding) : BaseViewHolder(binding.root) {
    fun bind(item: CurrencyModel) {
    }
}