package com.example.p_navadmin.Public

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.p_navadmin.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.event_description.*

class EventDescription : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_description)

        // get passed data
        val store = intent.getStringExtra("store")

        // connect to firestore
        val db = Firebase.firestore

        db.collection("Event").document("$store")
            .get()
            .addOnSuccessListener { result ->
                descriptionTitle.text = result.getField<String>("title")
                descriptionDate.text = result.getField<String>("start")+" until "+ result.getField<String>("end")
                descriptionContent.text = result.getField<String>("description")
                val url = result.getField<String>("url")
                Picasso.get().load(url).into(descriptionImage)
            }

    }
}