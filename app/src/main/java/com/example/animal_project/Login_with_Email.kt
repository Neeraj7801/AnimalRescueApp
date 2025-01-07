// File: Login_with_Email.kt
package com.example.animal_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Login_with_Email : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_with_email)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Find Views
        val emailEditText = findViewById<EditText>(R.id.et1)
        val passwordEditText = findViewById<EditText>(R.id.et2)
        val loginButton = findViewById<Button>(R.id.btLogin)
        val signUpTextView = findViewById<TextView>(R.id.tvDont)

        // Navigate to Signup Screen
        signUpTextView.setOnClickListener {
            val intent = Intent(this, Signup_with_Email::class.java)
            startActivity(intent)
        }

        // Handle Login Button Click
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Function to Authenticate User
    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    // Navigate to User Type Screen
                    startActivity(Intent(this, userType::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Login Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
