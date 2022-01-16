package com.example.authactivity.ui.mycontacts

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.authactivity.base.BaseAdapter
import com.example.authactivity.base.BaseViewHolder
import com.example.authactivity.databinding.ItemContactsBinding
import com.example.authactivity.databinding.ItemFragmentContactsBinding
import com.example.authactivity.model.ListData
import com.example.authactivity.ui.mycontacts.bottomSheet.AdapterBottomSheet
import kotlinx.android.synthetic.main.item_fragment_contacts.view.*

class ContactAdapter(private val listener: ClickListener):BaseAdapter() {

    private var items = mutableListOf<ListData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val binding = ItemFragmentContactsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val bindingEmpty = ItemContactsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return if (viewType == AdapterBottomSheet.VIEW_TYPE_DATA) ListViewHolder(
            binding
        )
        else EmptyListViewHolder(
            bindingEmpty
        )
    }

    override fun getItemCount(): Int {
        return if (items.count() == 0) 1
        else items.count()
    }

    override fun getItemViewType(position: Int): Int {
        return if (items.count() == 0) VIEW_TYPE_EMPTY
        else VIEW_TYPE_DATA
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val type = getItemViewType(position)
        if (type == VIEW_TYPE_DATA) setupItemListViewHolder(holder as ListViewHolder, position)
    }

    private fun setupItemListViewHolder(holder: ListViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener.onItemClick(item)
        }
        holder.itemView.setOnLongClickListener {
            listener.onLongItemClick(item)
            true
        }
    }

    fun addItems(item: MutableList<ListData>) {
        items = item
        notifyDataSetChanged()
    }

    fun addItem(item: ListData) {
        items.add(item)
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }

    fun restoreItem(item: ListData?, position: Int){
        if (item != null) {
            items.add(position, item)
            notifyItemRangeChanged(position, itemCount)
        }
    }

    companion object {
        const val VIEW_TYPE_DATA = 1
        const val VIEW_TYPE_EMPTY = 2
    }
}

class ListViewHolder(var binding: ItemFragmentContactsBinding): BaseViewHolder(binding.root){
    fun bind(item: ListData) {
        itemView.svs_txt.text = item.name
    }
}

class EmptyListViewHolder(var binding: ItemContactsBinding): BaseViewHolder(binding.root)

interface ClickListener {
    fun onItemClick(item: ListData)
    fun onLongItemClick(item: ListData)
}