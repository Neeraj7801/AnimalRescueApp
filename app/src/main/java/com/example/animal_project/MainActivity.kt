package com.example.animal_project

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Navigation Drawer
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.nav_view)

        // Set Navigation Item Selected Listener
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_dashboard -> {
                // Handle Dashboard navigation (if needed)
            }
            R.id.nav_adoption -> {
                // Open Animals for Adoption Activity
                startActivity(Intent(this, AdoptionActivity::class.java))
            }
            R.id.nav_donations -> {
                // Open Donations Activity
                startActivity(Intent(this, DonationsActivity::class.java))
            }
            R.id.nav_logout -> {
                // Handle logout
                finish()
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START) // Close the drawer after selection
        return true
    }
}
