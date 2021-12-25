package com.example.authactivity.ui.mycontacts.bottomSheet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.authactivity.R
import com.example.authactivity.model.ListData
import com.example.authactivity.ui.mycontacts.ClickListener
import kotlinx.android.synthetic.main.item_fragment_contacts.view.*

class AdapterBottomSheet(private val listener: ClickListener): RecyclerView.Adapter<BaseListViewHolder>() {

    private var items = mutableListOf<ListData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListViewHolder {
        return if (viewType == VIEW_TYPE_DATA) ListViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_contacts_bottom_sheet, parent, false)
        ) else EmptyListViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_adapter_bottom_sheet, parent, false)
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

    override fun onBindViewHolder(holder: BaseListViewHolder, position: Int) {
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
//        notifyItemRangeInserted(items.lastIndex, items.count()-1)
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

open class BaseListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){}

class ListViewHolder(itemView: View): BaseListViewHolder(itemView){
    fun bind(item: ListData) {
        itemView.svs_txt.text = item.name
    }
}

class EmptyListViewHolder(itemView: View): BaseListViewHolder(itemView)

interface ClickListener {
    fun onItemClick(item: ListData)
    fun onLongItemClick(item: ListData)
}
