package com.example.foodizone.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.foodizone.R

class LoginActivity : AppCompatActivity() {
    lateinit var button: Button
    lateinit var txtRegister : TextView
    lateinit var phoneEt : EditText
    lateinit var passwordEt : EditText
    lateinit var txtForgetPass : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        phoneEt = findViewById(R.id.et_mob_login)
        passwordEt = findViewById(R.id.et_pass_login)
        button = findViewById(R.id.button_go)
        txtRegister = findViewById(R.id.txt_signup)
        txtForgetPass = findViewById(R.id.tv_forgetPass_login)


        txtRegister.setOnClickListener {

            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            val phone = phoneEt.text.toString()
            val pswrd = passwordEt.text.toString()

            //intent to start activity
            val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
            intent.putExtra("Phone", phone)
            intent.putExtra("Password",pswrd)
            startActivity(intent)
        }

        txtForgetPass.setOnClickListener{
            val intent = Intent(this@LoginActivity,
                ForgetPasswordActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onResume() {
        // TODO Auto-generated method stub
        super.onResume()
        phoneEt.setText("")
        passwordEt.setText("")
    }
}
