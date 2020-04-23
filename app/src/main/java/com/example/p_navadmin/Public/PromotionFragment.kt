package com.example.p_navadmin.Public


import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.p_navadmin.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_promotion.*

/**
 * A simple [Fragment] subclass.
 */
class PromotionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_promotion, container, false)

        val db = Firebase.firestore

        val eventList = mutableListOf<Event>()

        // get the event list from firestore
        db.collection("Event")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    d("firebase", "success : ${document.id} url : ${document.getField<String>("url")}")
                    val store = document.id
                    val title = document.getField<String>("title")
                    val url = document.getField<String>("url")
                    eventList.add(Event("$store", "$title", "$url"))
                }

                // setup recycler view
                recyclerView.apply {
                    d("firebase", "recyclerView")
                    layoutManager = LinearLayoutManager(this@PromotionFragment.requireContext())
                    adapter = PublicAdapter(eventList)
                }
            }

        return root
    }


}
