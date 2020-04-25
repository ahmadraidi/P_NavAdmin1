package com.example.p_navadmin.StoreRetailer

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.p_navadmin.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.login_store_retailer.*

class Login_ShopRetailer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_store_retailer)

        val db = Firebase.firestore

        buttonLoginSR.setOnClickListener {

            // continue if username and password are not empty
            if (inputUsernameSR.text.isNotEmpty() && inputPasswordSR.text.isNotEmpty()) {

                db.collection("Login Store Retailer")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            val usernameSR = document.getField<String>("username")
                            val passwordSR = document.getField<String>("password")
                            val iUsernameSR = inputUsernameSR.text.toString()
                            val iPasswordSR = inputPasswordSR.text.toString()

                            if (usernameSR.equals(iUsernameSR)) {
                                d("firebase", "SR username ${document.getField<String>("username")}")
                                if (passwordSR.equals(iPasswordSR)) {
                                    d("firebase", "SR password ${document.getField<String>("password")}")
                                    val intent = Intent(this, Shop_Retailer_Promotion_Event::class.java)
                                    intent.putExtra("store", usernameSR)
                                    startActivity(intent)
                                } else {
                                    Toast.makeText(applicationContext, "Wrong Password or Username", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                Toast.makeText(applicationContext, "Wrong Password or Username", Toast.LENGTH_SHORT).show()
                            }
                            
                        }

                    }
                    .addOnFailureListener { e ->
                        d("firebase", "error SR ", e)
                    }

            }
        }
    }
}