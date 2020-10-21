package com.example.foodizone.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.foodizone.R

class ForgetPasswordActivity : AppCompatActivity() {
    lateinit var  btnNext : Button
    lateinit var etEmail : EditText
    lateinit var etPhone : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        btnNext = findViewById(R.id.btn_next_forgetp)
        etPhone = findViewById(R.id.et_mob_forgetp)
        etEmail = findViewById(R.id.et_email_forgetp)

        btnNext.setOnClickListener{

            val email = etEmail.text.toString()
            val phone = etPhone.text.toString()

            val intent = Intent(this@ForgetPasswordActivity, ConfirmPassword::class.java)
            intent.putExtra("Email",email)
            intent.putExtra("Phone",phone)
            startActivity(intent)
        }
    }
    override fun onResume() {
        // TODO Auto-generated method stub
        super.onResume()
        etEmail.setText("")
        etPhone.setText("")
    }
}