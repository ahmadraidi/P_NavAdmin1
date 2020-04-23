package com.example.p_navadmin.Admin


import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.p_navadmin.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_store_details.view.*

/**
 * A simple [Fragment] subclass.
 */
class StoreDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_store_details, container, false)
        val root = inflater.inflate(R.layout.fragment_store_details, container, false)

        // access a cloud firestore instance
        val db = Firebase.firestore

        val btnSearch = root.findViewById<Button>(R.id.buttonSearch)
        val btnUpdate = root.findViewById<FloatingActionButton>(R.id.buttonUpdate)

        // button search pressed (SEARCH)
        btnSearch.setOnClickListener {
            val storeName = root.search_current_store.text.toString().toLowerCase()
            d("firebase", "just click ${storeName}")

            if (storeName.isNotEmpty()) {
                // get data from firestore database from document = storeName
                db.collection("Store").document("${storeName}")
                    .get()
                    .addOnSuccessListener { result ->
                        d("firebase", "field is : ${result.getField<String>("lot")}")
                        d("firebase", "field is : ${result.getField<String>("category")}")
                        val lot = result.getField<String>("lot")
                        val category = result.getField<String>("category")

                        // display on text if not null
                        if (lot != null && category != null) {
                            root.update_lot_no.setText("${lot}")
                            root.updateCategory.setText("${category}")
                        } else {
                            root.update_lot_no.text.clear()
                            root.updateCategory.text.clear()
                        }

                    }
                    .addOnFailureListener { e ->
                        d("firebase", "error", e)
                    }
            }
            else
            {
                // if search store name is empty
            }
        }

        // Button update pressed
        btnUpdate.setOnClickListener {

            // check if the store name exist
            val storeName = root.search_current_store.text.toString().toLowerCase()
            val newStoreName = root.update_new_store_name.text.toString().toLowerCase()
            val newLotNo = root.update_lot_no.text.toString().toLowerCase()
            val newCategory = root.updateCategory.text.toString().toLowerCase()

            if (storeName.isNotEmpty() && newStoreName.isNotEmpty() && newLotNo.isNotEmpty() && newCategory.isNotEmpty()) {

                db.collection("Store")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            d("firebase", "document is  ${document.id}")

                            if(document.id.equals(storeName)) {
                                d("firebase", "found store name")
                                // if name exist
                                // if new name as current name the same then ignore
                                if (storeName != newStoreName) {

                                    // delete and re-add new updated info
                                    db.collection("Store").document("${storeName}")
                                        .delete()
                                        .addOnSuccessListener { result ->
                                            d("firebase", "deleted")
                                        }
                                        .addOnFailureListener { e ->
                                            d("firebase", "error ",e)
                                        }
                                    // re-add new info
                                    val store = hashMapOf(
                                        "lot" to "$newLotNo",
                                        "category" to "$newCategory"
                                    )
                                    db.collection("Store").document("${newStoreName}")
                                        .set(store)
                                        .addOnSuccessListener { result ->
                                            d("firebase", "succesfully add new data")
                                        }
                                        .addOnFailureListener { e ->
                                            d("firebase", "error ", e)
                                        }
                                }


                            }
                        }
                    }
                    .addOnFailureListener { e ->
                        d("firebase", "error ",e)
                    }
            }

            else if (storeName.isNotEmpty() && newLotNo.isNotEmpty() && newCategory.isNotEmpty()) {

                db.collection("Store")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            d("firebase", "document is  ${document.id}")

                            if(document.id.equals(storeName)) {
                                d("firebase", "found store name")
                                // if name exist
                                // if new name as current name the same then ignore
                                if (storeName != newStoreName) {

                                    // delete and re-add new updated info
                                    db.collection("Store").document("${storeName}")
                                        .delete()
                                        .addOnSuccessListener { result ->
                                            d("firebase", "deleted")
                                        }
                                        .addOnFailureListener { e ->
                                            d("firebase", "error ",e)
                                        }
                                    // re-add new info
                                    val store = hashMapOf(
                                        "lot" to "$newLotNo",
                                        "category" to "$newCategory"
                                    )
                                    db.collection("Store").document("${storeName}")
                                        .set(store)
                                        .addOnSuccessListener { result ->
                                            d("firebase", "succesfully add new data")
                                        }
                                        .addOnFailureListener { e ->
                                            d("firebase", "error ", e)
                                        }
                                }


                            }
                        }
                    }
                    .addOnFailureListener { e ->
                        d("firebase", "error ",e)
                    }
            }
        }

        return root
    }


}
