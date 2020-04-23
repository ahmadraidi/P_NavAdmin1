package com.example.p_navadmin.Admin

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import com.example.p_navadmin.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.confirmation.*

class AdminConfirmation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.confirmation)
        d("firebase", "screen open")

        val store = intent.getStringExtra("store")

        // give accesss firestore
        val db = Firebase.firestore

        var description = ""
        var start = ""
        var end = ""
        var title = ""
        var imageName = ""
        var url = ""

        db.collection("Confirmation Event").document("$store")
            .get()
            .addOnSuccessListener { result ->
                d("firebase", "field is : ${result.getField<String>("description")}")
                description = result.getField<String>("description").toString()
                start = result.getField<String>("start").toString()
                end = result.getField<String>("end").toString()
                title = result.getField<String>("title").toString()
                imageName = result.getField<String>("image name").toString()
                url = result.getField<String>("url").toString()

                confirmationImageName.text = imageName
                confirmationTitle.text = title
                confirmationStart.text = start
                confirmationEnd.text = end
                confirmationDescription.text = description
                confirmationDescription.movementMethod = ScrollingMovementMethod()
            }

        confirmFloat.setOnClickListener {

            if (title.isNotEmpty() && start.isNotEmpty() && end.isNotEmpty() && description.isNotEmpty()) {
                // send to the event db
                val event = hashMapOf(
                    "title" to "$title",
                    "start" to "$start",
                    "end" to "$end",
                    "description" to "$description",
                    "image name" to "$imageName",
                    "url" to "$url"

                )

                db.collection("Event").document("$store")
                    .set(event)
                    .addOnSuccessListener { result ->
                        d("firebase", "updated")
                    }
                    .addOnFailureListener { e ->
                        d("firebase", "error ", e)
                    }

                // delete
                db.collection("Confirmation Event").document("$store")
                    .delete()
                    .addOnSuccessListener { result ->
                        d("firebase", "deleted")
//                        startActivity(Intent(this, Admin_Home::class.java))

                        val intent = Intent(this, Admin_Home::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or
                                Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish() // finish the current activity
                    }
                    .addOnFailureListener { e ->
                        d("firebase", "error ", e)
                    }
            }
        }

        rejectFloat.setOnClickListener {
            // delete
            db.collection("Confirmation Event").document("$store")
                .delete()
                .addOnSuccessListener { result ->
                    d("firebase", "deleted")

//                    startActivity(Intent(this, Admin_Home::class.java))
                    val intent = Intent(this, Admin_Home::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or
                            Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish() // finish the current activity

                }
                .addOnFailureListener { e ->
                    d("firebase", "error ", e)
                }

        }
    }
}