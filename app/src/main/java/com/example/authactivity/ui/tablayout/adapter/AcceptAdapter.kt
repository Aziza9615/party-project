package com.example.authactivity.ui.tablayout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.authactivity.base.BaseAdapter
import com.example.authactivity.base.BaseViewHolder
import com.example.authactivity.databinding.ItemFragmentContactsBinding
import com.example.authactivity.databinding.ItemTabBinding
import com.example.authactivity.model.AcceptData
import com.example.authactivity.model.ContactData
import com.example.authactivity.ui.tablayout.fragment.AcceptFragment
import kotlinx.android.synthetic.main.item_bottom_sheet.view.*
import kotlinx.android.synthetic.main.item_bottom_sheet.view.svs_category
import kotlinx.android.synthetic.main.item_tab.view.*

class AcceptAdapter(private val listener: ClickListenerAccept): BaseAdapter() {

    private var items = mutableListOf<AcceptData>()

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
        holder.itemView.setOnClickListener {
            listener.onListClick(item)
        }
        holder.itemView.setOnLongClickListener {
            listener.onLongItemClickList(item)
            true
        }
    }

    fun addItems(item: MutableList<AcceptData>) {
        items = item
        notifyDataSetChanged()
    }

    fun addItem(item: AcceptData) {
        items.add(item)
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }

    class AcceptViewHolder(var binding: ItemTabBinding): BaseViewHolder(binding.root){
        fun bind(item: AcceptData) {
            itemView.svs_category.text = item.category
            itemView.amount_tab.text = item.amount.toString()
        }
    }

    interface ClickListenerAccept {
        fun onListClick(item: AcceptData)
        fun onLongItemClickList(item: AcceptData)
    }
}
