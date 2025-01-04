package com.example.animal_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class userType : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_type)
        findViewById<Button>(R.id.rescuerbtn).setOnClickListener {
            startActivity(Intent(this,rescuer_registration::class.java))
        }
        findViewById<Button>(R.id.helperbtn).setOnClickListener {
            startActivity(Intent(this,helper_registration::class.java))
        }
    }
}