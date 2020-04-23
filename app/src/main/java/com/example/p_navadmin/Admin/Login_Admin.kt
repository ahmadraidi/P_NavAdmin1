package com.example.p_navadmin.Admin

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import com.example.p_navadmin.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.login_admin.*

class Login_Admin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_admin)

        // Access a Cloud Firestore instance from your Activity
        val db = Firebase.firestore

        buttonLoginAdmin.setOnClickListener {

            // continue if username and password are not empty
            if (inputUsernameAdmin.text.isNotEmpty() && inputPasswordAdmin.text.isNotEmpty()) {

                db.collection("Login").document("admin")
                    .get()
                    .addOnSuccessListener { result ->
                        d("firebase", "admin username ${result.getField<String>("username")}")
                        d("firebase", "admin password ${result.getField<String>("password")}")
                        val usernameAdmin = result.getField<String>("username")
                        val passwordAdmin = result.getField<String>("password")
                        val iUsernameAdmin = inputUsernameAdmin.text.toString()
                        val iPasswordAdmin = inputPasswordAdmin.text.toString()

                        if (usernameAdmin.equals(iUsernameAdmin) && passwordAdmin.equals(iPasswordAdmin)) {
                            startActivity(Intent(this, Admin_Home::class.java))
                        }
                        else {
                            // if false
                        }
                    }
                    .addOnFailureListener { e ->
                        d("firebase", "error", e)
                    }
            }
        }
    }
}