package com.example.testtodoapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUpActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        // UI
        val newEmail = findViewById<EditText>(R.id.idSignUpEmail)
        val newPassword = findViewById<EditText>(R.id.idSignUpPassword)
        val signUp = findViewById<Button>(R.id.idSignUpButton)


        signUp.setOnClickListener {
            if ( !newEmail.text.contains("@") || !newEmail.text.contains(".") ) {
                Toast.makeText(applicationContext, "Please enter a valid email", Toast.LENGTH_LONG).show()
            } else {
                if ( newPassword.text.toString().length < 4) {
                    Toast.makeText(applicationContext, "Password must have min. 4 characters", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(applicationContext, "You're all signed up!", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("Email", newEmail.text.toString())
                    intent.putExtra("Password", newPassword.text.toString())
                    startActivity(intent)
                }
            }
        }


    }
}

