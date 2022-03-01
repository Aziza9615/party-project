package com.example.authactivity.ui.mycontacts.bottomSheet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.authactivity.base.BaseAdapter
import com.example.authactivity.base.BaseViewHolder
import com.example.authactivity.databinding.ItemAdapterBottomSheetBinding
import com.example.authactivity.databinding.ItemFragmentContactsBinding
import com.example.authactivity.model.ListData
import kotlinx.android.synthetic.main.item_fragment_contacts.view.*


class AdapterBottomSheet(private val listener: ClickListenerBottom) : BaseAdapter() {

    private var items = mutableListOf<ListData>()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = ItemFragmentContactsBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        val bindingEmpty = ItemAdapterBottomSheetBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
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
        if (type == VIEW_TYPE_DATA) setupItemListViewHolder(holder as ListBottomViewHolder,
            position)
        else setupButton(holder as EmptyListViewHolder)
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

    private fun setupButton(holder: EmptyListViewHolder) {
        holder.bind()
        holder.binding.newBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val fragment = context as FragmentActivity
                val fm: FragmentManager = fragment.supportFragmentManager
                val alertDialog = AddBottomSheetFragment()
                alertDialog.show(fm, "alert_edit")
            }
        })
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

    companion object {
        const val VIEW_TYPE_DATA = 1
        const val VIEW_TYPE_EMPTY = 2
    }
}

class ListBottomViewHolder(var binding: ItemFragmentContactsBinding): BaseViewHolder(binding.root){
    fun bind(item: ListData) {
        itemView.svs_txt.text = item.name
    }
}

class EmptyListViewHolder(var binding: ItemAdapterBottomSheetBinding): BaseViewHolder(binding.root) {
    fun bind() {
    }
}

interface ClickListenerBottom {
    fun onItemClickBottom(item: ListData)
    fun onLongItemClickBottom(item: ListData)
}

