package com.example.p_navadmin

import android.content.Intent
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.p_navadmin.Admin.AdminConfirmation
import com.example.p_navadmin.Admin.EventList
import kotlinx.android.synthetic.main.row_verify_list.view.*

class VerifyListAdapter(private val eventList: MutableList<EventList>) : RecyclerView.Adapter<VerifyListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val event: TextView = itemView.listEvent
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_verify_list, parent, false)
        val holder = ViewHolder(view)

        view.setOnClickListener {
            val intent = Intent(parent.context, AdminConfirmation::class.java)
            intent.putExtra("store", eventList[holder.adapterPosition].store)
            parent.context.startActivity(intent)
        }
        return holder
    }

    override fun getItemCount() = eventList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.event.text = eventList[position].store
        d("firebase", "${eventList[position].store}")
    }


}
