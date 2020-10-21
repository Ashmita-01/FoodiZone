package com.example.foodizone.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.foodizone.R

class RegisterActivity : AppCompatActivity() {

    lateinit var etName : EditText
    lateinit var etEmail : EditText
    lateinit var etPhone : EditText
    lateinit var etDelivery : EditText
    lateinit var etPassword : EditText
    lateinit var etConfPassword : EditText
    lateinit var btnRegister :Button

    lateinit var coordinateLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var frameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        title="Register Yourself"

        etName = findViewById(R.id.et_name_register)
        etEmail = findViewById(R.id.et_email_register)
        etPhone = findViewById(R.id.et_mob_register)
        etDelivery = findViewById(R.id.et_delivery_register)
        etPassword = findViewById(R.id.et_pass_register)
        etConfPassword = findViewById(R.id.et_Confirmpass_register)
        btnRegister = findViewById(R.id.bt_register)

        coordinateLayout=findViewById(R.id.coordinatelayout)
        toolbar=findViewById(R.id.toolbar)
        frameLayout=findViewById(R.id.frameLayout)

        setUpToolbar()

        btnRegister.setOnClickListener{
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val mob = etPhone.text.toString()
            val pass = etPassword.text.toString()
            val confpass = etConfPassword.text.toString()
            val delivery = etDelivery.text.toString()

            val intent = Intent(this@RegisterActivity,
                NewActivity::class.java)
            intent.putExtra("Name",name)
            intent.putExtra("Email",email)
            intent.putExtra("Phone",mob)
            intent.putExtra("Password",pass)
            intent.putExtra("ConfirmPassword",confpass)
            intent.putExtra("Delivery",delivery)
            startActivity(intent)
        }
    }
    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title="Register Yourself"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }
    }

    override fun onResume() {
        // TODO Auto-generated method stub
        super.onResume()
        etName.setText("")
        etEmail.setText("")
        etPassword.setText("")
        etConfPassword.setText("")
        etPhone.setText("")
        etDelivery.setText("")
    }
}
