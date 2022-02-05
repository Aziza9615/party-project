package com.example.authactivity.ui.mycontacts.bottomSheet

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.authactivity.base.BaseAdapter
import com.example.authactivity.base.BaseViewHolder
import com.example.authactivity.databinding.ItemAdapterBottomSheetBinding
import com.example.authactivity.databinding.ItemFragmentContactsBinding
import com.example.authactivity.model.ContactData
import com.example.authactivity.model.ListData
import kotlinx.android.synthetic.main.item_fragment_contacts.view.*

class AdapterBottomSheet(private val listener: ClickListenerBottom) : BaseAdapter() {

    private var items = mutableListOf<ContactData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = ItemFragmentContactsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val bindingEmpty = ItemAdapterBottomSheetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return if (viewType == VIEW_TYPE_DATA) ListBottomViewHolder(
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
        if (type == VIEW_TYPE_DATA) setupItemListViewHolder(holder as ListBottomViewHolder, position)
    }

    private fun setupItemListViewHolder(holder: ListBottomViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener.onItemClickBottom(item)
        }
        holder.itemView.setOnLongClickListener {
            listener.onLongItemClickBottom(item)
            true
        }
    }

    fun addItems(item: MutableList<ContactData>) {
        items = item
        notifyDataSetChanged()
    }

    fun addItem(item: ContactData) {
        items.add(item)
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }

    fun restoreItem(item: ContactData?, position: Int){
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

class ListBottomViewHolder(var binding: ItemFragmentContactsBinding): BaseViewHolder(binding.root){
    fun bind(item: ContactData) {
        itemView.svs_txt.text = item.name
    }
}

class EmptyListViewHolder(var binding: ItemAdapterBottomSheetBinding): BaseViewHolder(binding.root)

interface ClickListenerBottom {
    fun onItemClickBottom(item: ContactData)
    fun onLongItemClickBottom(item: ContactData)
}
