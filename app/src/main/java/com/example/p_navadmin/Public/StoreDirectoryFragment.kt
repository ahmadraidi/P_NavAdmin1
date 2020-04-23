package com.example.p_navadmin.Public


import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.p_navadmin.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass.
 */
class StoreDirectoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_store_directory, container, false)

        val db = Firebase.firestore

        val recyclerViewStore = root.findViewById<RecyclerView>(R.id.recyclerViewStore)
        val editSearch = root.findViewById<EditText>(R.id.editSearch)

        val store = mutableListOf<Store>()

        // get the data from firebase( firestore )
        db.collection("Store")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    d(
                        "storeList",
                        " name: ${document.id} => ${document.getField<String>("lot")}, ${document.getField<String>(
                            "category"
                        )}"
                    )
                    val storeName = document.id
                    var storeLot = document.getField<String>("lot").toString()
                    val storeCategory = document.getField<String>("category").toString()
                    when {
                        storeLot.contains('f') -> {
                            storeLot = "Level 1 - " + storeLot
                        }
                        storeLot.contains('g') -> {
                            storeLot = "Level G - " + storeLot
                        }
                        storeLot.contains('m') -> {
                            storeLot = "Level M - " + storeLot
                        }
                        storeLot.contains('s') -> {
                            storeLot = "Level 2 - " + storeLot
                        }
                        storeLot.contains('t') -> {
                            storeLot = "Level 3 - " + storeLot
                        }
                    }

                    store.add(Store(storeName, storeLot, storeCategory))

                }

                recyclerViewStore.apply {
                    layoutManager =
                        LinearLayoutManager(this@StoreDirectoryFragment.requireContext())
                    adapter = StoreListAdapter(store)
                }
            }

        editSearch.addTextChangedListener {
            val inputSearch = editSearch.text.toString().toLowerCase()
            d("faris", "$inputSearch")
            db.collection("Store")
                .get()
                .addOnSuccessListener { result ->
                    store.clear()
                    for (document in result) {
                        val storeName = document.id
                        var storeLot = document.getField<String>("lot").toString()
                        val storeCategory = document.getField<String>("category").toString()

                        if (storeName.contains(inputSearch)) {
                            d("faris", "addtextChange : $storeName")
                            when {
                                storeLot.contains('f') -> {
                                    storeLot = "Level 1 - " + storeLot
                                }
                                storeLot.contains('g') -> {
                                    storeLot = "Level G - " + storeLot
                                }
                                storeLot.contains('m') -> {
                                    storeLot = "Level M - " + storeLot
                                }
                                storeLot.contains('s') -> {
                                    storeLot = "Level 2 - " + storeLot
                                }
                                storeLot.contains('t') -> {
                                    storeLot = "Level 3 - " + storeLot
                                }
                            }

                            store.add(Store(storeName, storeLot, storeCategory))
                        }
                    }
                    recyclerViewStore.apply {
                        layoutManager =
                            LinearLayoutManager(this@StoreDirectoryFragment.requireContext())
                        adapter = StoreListAdapter(store)
                    }
                }


        }
        return root
    }
}

// store data
data class Store (
    val name: String,
    val location: String,
    val category: String
)
