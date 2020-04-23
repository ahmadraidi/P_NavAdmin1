package com.example.p_navadmin

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class Public_use : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.public_use)

        Handler().postDelayed({
            startActivity(Intent(this, PublicMain::class.java))
        }, 3000)
    }
}