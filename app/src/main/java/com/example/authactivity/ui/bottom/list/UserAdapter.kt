package com.example.authactivity.ui.bottom.list

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.authactivity.R
import com.example.authactivity.model.ListData

class UserAdapter(val c:Context,val userList:ArrayList<ListData>):RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var listArray = mutableListOf<ListData>()

    inner class UserViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
        var name: TextView
        var category: TextView
        var mbNum: TextView
        var mMenus: ImageView

        init {
            name = v.findViewById<TextView>(R.id.mTitle)
            category = v.findViewById<TextView>(R.id.mSubTitle)
            mbNum = v.findViewById<TextView>(R.id.amount)
            mMenus = v.findViewById(R.id.mMenus)
            mMenus.setOnClickListener { popupMenus(it) }
        }

        private fun popupMenus(v: View) {
            val position = userList[adapterPosition]
            val popupMenus = PopupMenu(c, v)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.editText -> {
                        val v = LayoutInflater.from(c).inflate(R.layout.items_add, null)
                        val name = v.findViewById<EditText>(R.id.userName)
                        val category = v.findViewById<EditText>(R.id.category)
                        val number = v.findViewById<EditText>(R.id.userAmount)
                        AlertDialog.Builder(c)
                            .setView(v)
                            .setPositiveButton("Ok") { dialog, _ ->
                                position.userName = name.text.toString()
                                position.userSub = name.text.toString()
                                position.userAm = number.text.toString()
                                notifyDataSetChanged()
                                Toast.makeText(c, "User Information is Edited", Toast.LENGTH_SHORT)
                                    .show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("Cancel") { dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                            .show()
                        true
                    }
                    R.id.delete -> {
                        AlertDialog.Builder(c)
                            .setTitle("Delete")
                            .setIcon(R.drawable.ic_warning)
                            .setMessage("Are you sure delete this Information")
                            .setPositiveButton("Yes") { dialog, _ ->
                                userList.removeAt(adapterPosition)
                                notifyDataSetChanged()
                                Toast.makeText(c, "Deleted this Information", Toast.LENGTH_SHORT)
                                    .show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("No") { dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                            .show()

                        true
                    }
                    else -> true
                }
            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(menu, true)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.item_list, parent, false)
        return UserViewHolder(v)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val newList = userList[position]
        holder.name.text = newList.userName
        holder.category.text = newList.userSub
        holder.mbNum.text = newList.userAm
        val item = listArray[position]
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun addItems(items: MutableList<ListData>) {
        listArray = items
        notifyDataSetChanged()
    }
}

interface ClickListener {
    fun onItemClick(item: ListData)
}
