package com.example.authactivity.ui.bottom.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.authactivity.R

class ListAdapter(val context: ListFragment, val userList: ArrayList<com.example.authactivity.model.BottomModel>):RecyclerView. Adapter<ListAdapter.ListViewHolder>() {

    inner class ListViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.mTittle)
        val category = view.findViewById<TextView>(R.id.subTittle)
        val mbNum = view.findViewById<TextView>(R.id.amount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_list,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val newList = userList[position]
        holder.name.text = newList.userName
        holder.category.text = newList.userSub
        holder.mbNum.text = newList.userAm
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}