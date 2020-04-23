package com.example.p_navadmin.Public

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.p_navadmin.R
import kotlinx.android.synthetic.main.store_list_row.view.*

class StoreListAdapter(val store: MutableList<Store>) : RecyclerView.Adapter<StoreListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.store_list_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = store.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.storeName.text = store[position].name.toUpperCase()
        holder.storeLocation.text = store[position].location
        holder.storeCategory.text = store[position].category.toUpperCase()
        holder.storeChar.text = store[position].name.toCharArray().first().toString().toUpperCase()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val storeName: TextView = itemView.textStoreName
        val storeLocation: TextView = itemView.textLocation
        val storeCategory: TextView = itemView.textCategory
        val storeChar: TextView = itemView.textChar
    }

}
