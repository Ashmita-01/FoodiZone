package com.example.foodizone.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.foodizone.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            val intent= Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
        },3000)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}
