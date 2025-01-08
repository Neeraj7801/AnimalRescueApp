package com.example.animal_project

import android.os.Bundle
import android.view.Gravity
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class rescuerhomepage : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rescuerhomepage)

        // Initialize DrawerLayout and NavigationView
        drawerLayout = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.navigation_view)
//        val menuButton: ImageButton = findViewById(R.id.menu_button)

        // Open drawer on menu button click
//        menuButton.setOnClickListener {
//            drawerLayout.openDrawer(GravityCompat.START)
//        }

        // Handle navigation item clicks
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_dashboard -> {
                    // Handle Dashboard action
                }
                R.id.nav_profile -> {
                    // Handle Profile action
                }
                R.id.nav_settings -> {
                    // Handle Settings action
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START) // Close drawer after selection
            true
        }
    }
}
