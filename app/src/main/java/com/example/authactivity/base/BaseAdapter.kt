package com.example.authactivity.base

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.authactivity.ui.mycontacts.ContactActivity
import com.example.authactivity.ui.mycontacts.EmptyListViewHolder

abstract class BaseAdapter : RecyclerView.Adapter<BaseViewHolder>()

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
}