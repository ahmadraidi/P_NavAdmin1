package com.example.p_navadmin.Admin


import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.p_navadmin.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_user_feedback.view.*

/**
 * A simple [Fragment] subclass.
 */
class UserFeedbackFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_user_feedback, container, false)

        val db = Firebase.firestore

        val ratingList = mutableListOf<RatingList>()

        val recyclerViewFeedback = root.findViewById<RecyclerView>(R.id.recyclerViewFeedback)

        //get the rate list
        db.collection("Rating")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val uid = document.id
                    val rate = document.getField<Float>("rate")
                    d("faris", "$uid and $rate")
                    ratingList.add(RatingList("$uid", "$rate"))
                }
                recyclerViewFeedback.apply {
                    layoutManager = LinearLayoutManager(this@UserFeedbackFragment.requireContext())
                    adapter = FeedbackListAdapter(ratingList)
                }
                d("faris", "${ratingList.size}")
            }
            .addOnFailureListener { e ->
                d("faris", "error ",e)
            }
//        for (i in 0..5) {
//            ratingList.add(RatingList("faris", "4.0"))
//        }
        d("faris", "${ratingList.size}")




        return root
    }


}
