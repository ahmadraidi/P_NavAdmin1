package com.example.p_navadmin.Public

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.p_navadmin.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.event_row.view.*

class PublicAdapter(private val event: MutableList<Event>) : RecyclerView.Adapter<PublicAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.eventTitle
        val image: ImageView = itemView.eventImg
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_row, parent, false)
        val holder = ViewHolder(view)
        view.setOnClickListener {
            val intent = Intent(parent.context, EventDescription::class.java)
            intent.putExtra("store", event[holder.adapterPosition].store)
            parent.context.startActivity(intent)
        }

        return holder
    }

    override fun getItemCount() = event.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = event[position].title
        Picasso.get().load(event[position].url).into(holder.image)
    }

}
