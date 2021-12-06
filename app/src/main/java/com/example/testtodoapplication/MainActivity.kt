package com.example.testtodoapplication


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // UI Elements
        val email = findViewById<EditText>(R.id.idSignUpEmail)
        val password = findViewById<EditText>(R.id.idSignUpPassword)
        val loginButton = findViewById<Button>(R.id.idSignUpButton)


        // Your credentials
        val userEmail = intent.getStringExtra("Email")
        val userPassword = intent.getStringExtra("Password")


        loginButton.setOnClickListener {

            val emailText = email.text.toString()
            val passwordText = password.text.toString()


            if (emailText == userEmail && passwordText == userPassword) {
                Toast.makeText(applicationContext, "Welcome!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, TodoActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "Invalid login. Please try again.", Toast.LENGTH_LONG).show()
            }


        }


    }


}

