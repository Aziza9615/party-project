package com.example.authactivity.ui.tablayout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.authactivity.base.BaseAdapter
import com.example.authactivity.base.BaseViewHolder
import com.example.authactivity.databinding.ItemTabBinding
import com.example.authactivity.model.ContactData
import com.example.authactivity.model.EditData
import com.example.authactivity.ui.mycontacts.ClickListener
import com.example.authactivity.ui.tablayout.fragment.AcceptFragment
import kotlinx.android.synthetic.main.item_tab.view.*

class EditAdapter : BaseAdapter() {

    private var items = mutableListOf<EditData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = ItemTabBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AcceptViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = items[position]
        val holder = holder as AcceptViewHolder
        holder.bind(item)
        holder.binding.svsCategory.text
        holder.binding.amountTab.text
    }

    fun addItems(item: MutableList<EditData>) {
        items = item
        notifyDataSetChanged()
    }

    fun addItem(item: EditData) {
        items.add(item)
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }

    class AcceptViewHolder(var binding: ItemTabBinding): BaseViewHolder(binding.root){
        fun bind(item: EditData) {
            itemView.svs_category.text = item.category.toString()
            itemView.amount_tab.text = item.amount.toString()
        }
    }
}
