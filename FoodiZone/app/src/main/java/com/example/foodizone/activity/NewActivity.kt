package com.example.foodizone.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.foodizone.R

class NewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        val intent = intent
        val name = intent.getStringExtra("Name")
        val email = intent.getStringExtra("Email")
        val phone = intent.getStringExtra("Phone")
        val pass = intent.getStringExtra("Password")
        val confpass = intent.getStringExtra("ConfirmPassword")
        val delivery = intent.getStringExtra("Delivery")

        val tvregister = findViewById<TextView>(R.id.tv_new)
        tvregister.text = "Name: "+name+"\nEmail: "+email+"\nPhone: "+phone+"\nDelivery Address: "+delivery+"\nPassword: "+pass+"\nConfirm Password: "+confpass
    }
}
