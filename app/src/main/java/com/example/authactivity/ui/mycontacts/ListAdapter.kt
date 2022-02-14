package com.example.authactivity.ui.mycontacts

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.authactivity.base.BaseAdapter
import com.example.authactivity.base.BaseViewHolder
import com.example.authactivity.databinding.ItemBottomSheetBinding
import com.example.authactivity.databinding.ItemContactsBinding
import com.example.authactivity.databinding.ItemFragmentContactsBinding
import com.example.authactivity.model.CategoryData
import com.example.authactivity.model.ContactData
import com.example.authactivity.ui.mycontacts.bottomSheet.AdapterBottomSheet
import kotlinx.android.synthetic.main.item_bottom_sheet.view.*
import kotlinx.android.synthetic.main.item_fragment_contacts.view.*

class ListAdapter(private val listener: ClickListenerList): BaseAdapter() {

    private var items = mutableListOf<ContactData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding =
            ItemFragmentContactsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        holder.itemView.setOnClickListener {
            listener.onListClick(item)
        }
        holder.itemView.setOnLongClickListener {
            listener.onLongItemClickList(item)
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

    class ContactViewHolder(var binding: ItemFragmentContactsBinding): BaseViewHolder(binding.root){
        fun bind(item: ContactData) {
            itemView.svs_category.text = item.category
        }
    }

    interface ClickListenerList {
        fun onListClick(item: ContactData)
        fun onLongItemClickList(item: ContactData)
    }
}
