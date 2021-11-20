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


        // Dummy credentials
        // Email: test@yahoo.com
        // Password: yyyyyy


        // UI Elements
        val email = findViewById<EditText>(R.id.idLoginEmail)
        val password = findViewById<EditText>(R.id.idLoginPassword)
        val loginButton = findViewById<Button>(R.id.idLoginButton)


        loginButton.setOnClickListener {

            val emailText = email.text.toString()
            val passwordText = password.text.toString()


            if (emailText == "test@yahoo.com" && passwordText == "yyyyyy") {
                Toast.makeText(this, "Welcome!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, TodoActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid login. Please try again.", Toast.LENGTH_LONG).show()
            }


        }


    }


}

