package com.example.foodizone.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.foodizone.R

class ConfirmPassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_password)

        val email = intent.getStringExtra("Email")
        val phone = intent.getStringExtra("Phone")

        val textview = findViewById<TextView>(R.id.tv_confirmpass)
        textview.text = "Email : " + email + "\nPhone : " + phone
    }
}
