package com.example.p_navadmin.Admin

import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.p_navadmin.R
import kotlinx.android.synthetic.main.feedback_list.view.*

class FeedbackListAdapter(private val ratingList: MutableList<RatingList>) : RecyclerView.Adapter<FeedbackListAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val uid: TextView = itemView.uid_name
        val rating_bar: RatingBar = itemView.ratingBar2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.feedback_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = ratingList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        d("faris", "onBind")
        holder.uid.text =ratingList[position].uid
        holder.rating_bar.rating = ratingList[position].rate.toFloat()
    }

}
