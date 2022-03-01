package com.example.authactivity.ui.mycontacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.authactivity.R
import com.example.authactivity.base.BaseAdapter
import com.example.authactivity.base.BaseViewHolder
import com.example.authactivity.databinding.ItemContactBinding
import com.example.authactivity.databinding.ItemFragmentContactsBinding
import com.example.authactivity.model.ContactData
import com.example.authactivity.model.ListData
import com.example.authactivity.ui.main.MainActivity
import com.example.authactivity.ui.mycontacts.bottomSheet.AdapterBottomSheet
import com.example.authactivity.ui.mycontacts.bottomSheet.AdapterBottomSheet.Companion.VIEW_TYPE_DATA
import kotlinx.android.synthetic.main.item_contact.view.*
import kotlinx.android.synthetic.main.item_fragment_contacts.view.*
import java.util.*
import kotlin.coroutines.coroutineContext
import android.content.Intent as Intent1

class ContactAdapter(private val listener: ClickListener):BaseAdapter() {

    lateinit var button: Button
    private var items = mutableListOf<ContactData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val binding = ItemFragmentContactsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val bindingEmpty = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        else setupButton (holder as EmptyListViewHolder)
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

    private fun setupButton(holder: EmptyListViewHolder) {
        holder.bind()
        holder.binding.newPresent.setOnClickListener (object : View.OnClickListener {
            override fun onClick(v: View?) {
                var gotoLevel = Intent1(v?.context, ContactActivity::class.java)
                v?.context?.startActivity(gotoLevel)
            }
        })
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

    companion object {
        const val VIEW_TYPE_DATA = 1
        const val VIEW_TYPE_EMPTY = 2
    }
}

class ListViewHolder(var binding: ItemFragmentContactsBinding): BaseViewHolder(binding.root){
    fun bind(item: ContactData) {
        itemView.svs_txt.text = item.name
    }
}

class EmptyListViewHolder(var binding: ItemContactBinding): BaseViewHolder(binding.root) {
    fun bind() {
    }
}

interface ClickListener {
    fun onItemClick(item: ContactData)
    fun onLongItemClick(item: ContactData)
    fun onButtonClick(item: ContactData)
}