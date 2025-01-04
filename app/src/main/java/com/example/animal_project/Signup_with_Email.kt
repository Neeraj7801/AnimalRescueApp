package com.example.animal_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Signup_with_Email : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup_with_email)
findViewById<TextView>(R.id.texthaveaccount).setOnClickListener {
    val intent = Intent(this,Login_with_Email::class.java)
    startActivity(intent)
}
        auth = FirebaseAuth.getInstance()
        val signMail = findViewById<EditText>(R.id.signmail)
        val signPhone = findViewById<EditText>(R.id.signphone)
        val passBox = findViewById<EditText>(R.id.passbx)
        val conPassBox = findViewById<EditText>(R.id.Conpassbx)
        val btSignIn = findViewById<Button>(R.id.btsignin)
        btSignIn.setOnClickListener {
            val email = signMail.text.toString().trim()
            val password = passBox.text.toString().trim()
            val confirmPassword = conPassBox.text.toString().trim()

            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Firebase Authentication
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user: FirebaseUser? = auth.currentUser
                        user?.sendEmailVerification()?.addOnCompleteListener { emailTask ->
                            if (emailTask.isSuccessful) {
                                Toast.makeText(
                                    this,
                                    "Verification email sent to $email",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                        Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this,Login_with_Email::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(
                            this,
                            "Signup Failed: ${task.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }



    }
}