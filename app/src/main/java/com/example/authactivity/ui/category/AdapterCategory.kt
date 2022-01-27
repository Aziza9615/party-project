package com.example.authactivity.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.authactivity.base.BaseAdapter
import com.example.authactivity.base.BaseViewHolder
import com.example.authactivity.databinding.ItemBottomSheetBinding
import com.example.authactivity.model.CategoryData
import com.example.authactivity.model.ListData
import kotlinx.android.synthetic.main.item_bottom_sheet.view.*

class AdapterCategory(private val listener: CategoryClickListener) : BaseAdapter() {

    private var items = mutableListOf<CategoryData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = ItemBottomSheetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = items[position]
        val holder = holder as CategoryViewHolder
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener.onCategoryClick(item)
        }
        holder.itemView.setOnLongClickListener {
            listener.onLongItemClickBottom(item)
            true
        }
    }

    fun addItems(item: MutableList<CategoryData>) {
        items = item
        notifyDataSetChanged()
    }

    fun addItem(item: CategoryData) {
        items.add(item)
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }

    fun restoreItem(item: CategoryData?, position: Int){
        if (item != null) {
            items.add(position, item)
            notifyItemRangeChanged(position, itemCount)
        }
    }

    class CategoryViewHolder(var binding: ItemBottomSheetBinding): BaseViewHolder(binding.root){
        fun bind(item: CategoryData) {
            itemView.svs_category.text = item.category
        }
    }

    interface CategoryClickListener {
        fun onCategoryClick(item: CategoryData)
        fun onLongItemClickBottom(item: CategoryData)
    }
}
