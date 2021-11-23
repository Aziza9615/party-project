package com.example.authactivity.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter(private val listener: ClickListener) : RecyclerView.Adapter<BaseViewHolder>()

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

interface ClickListener {
    fun onClick(v: View)
}