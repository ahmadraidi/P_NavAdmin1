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
import com.example.p_navadmin.VerifyListAdapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass.
 */
class AdminVerifyListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_admin_verify_list, container, false)

        // Access cloud Firestore
        val db = Firebase.firestore

        // get recyclerView
        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerView)

        val eventList = mutableListOf<EventList>()

        // get eventList that need confirmation
        db.collection("Confirmation Event")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    d("firebase", "${document.id}")
                    eventList.add(EventList("${document.id}"))
                }
                d("firebase", "size of : ${eventList.size}")

                // send data to recycler View (eventList)
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(this@AdminVerifyListFragment.requireContext())
                    adapter = VerifyListAdapter(eventList)
                }
            }
            .addOnFailureListener { e ->
                d("firebase", "error ", e)
            }




        return root
    }


}
