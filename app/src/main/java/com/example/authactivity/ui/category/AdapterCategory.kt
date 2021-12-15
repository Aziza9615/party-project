package com.example.authactivity.ui.category

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.authactivity.R
import com.example.authactivity.base.BaseViewHolder
import com.example.authactivity.databinding.ItemBottomSheetBinding
import com.example.authactivity.model.CategoryData

class AdapterCategory(private val listener: CategoryClickListener): com.example.authactivity.base.BaseAdapter() {

    private var items = mutableListOf<CategoryData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding =
                ItemBottomSheetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(
                binding
        )
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = items[position]
        val holder = holder as CategoryViewHolder
        holder.bind(item)
        holder.itemView.setOnClickListener { listener.onCategoryClick(item) }
    }

    fun addItems(item: MutableList<CategoryData>) {
        items = item
        notifyDataSetChanged()
    }
}

interface CategoryClickListener {
    fun onCategoryClick(item: CategoryData)
}

class CategoryViewHolder(var binding: ItemBottomSheetBinding) : BaseViewHolder(binding.root) {
    fun bind(item: CategoryData) {
        Glide.with(binding.ivSvs.context)
            .load(item.arrowImage)
            .placeholder(R.color.black)
            .into(binding.ivSvs)
        binding.svsTxt.text = item.name
    }
}
