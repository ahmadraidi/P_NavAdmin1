package com.example.p_navadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.p_navadmin.Admin.Login_Admin
import com.example.p_navadmin.StoreRetailer.Login_ShopRetailer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // administrator
        buttonAdministrator.setOnClickListener {
            startActivity(Intent(this, Login_Admin::class.java))
        }

        // store retailer
        buttonStoreRetailer.setOnClickListener {
            startActivity(Intent(this, Login_ShopRetailer::class.java))
        }

        // public use
        buttonPublicUse.setOnClickListener {
            startActivity(Intent(this, Public_use::class.java))
        }
    }
}
