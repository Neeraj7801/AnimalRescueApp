// File: report_animal.kt
package com.example.animal_project

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class report_animal : AppCompatActivity() {
    // Define all views and variables
    private lateinit var spinnerAnimalType: Spinner
    private lateinit var radioGroupCondition: RadioGroup
    private lateinit var editTextLocation: EditText
    private lateinit var buttonPickLocation: Button
    private lateinit var editTextDescription: EditText
    private lateinit var buttonUploadPhoto: Button
    private lateinit var editTextName: EditText
    private lateinit var editTextContact: EditText
    private lateinit var buttonSubmit: Button
    private lateinit var ivUploadedPhoto: ImageView
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val IMAGE_REQUEST_CODE = 101
    private val CAMERA_REQUEST_CODE = 102
    private val PERMISSION_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_animal)

        // Initialize views
        spinnerAnimalType = findViewById(R.id.spinnerAnimalType)
        radioGroupCondition = findViewById(R.id.radioGroupCondition)
        editTextLocation = findViewById(R.id.editTextLocation)
        buttonPickLocation = findViewById(R.id.buttonPickLocation)
        editTextDescription = findViewById(R.id.editTextDescription)
        buttonUploadPhoto = findViewById(R.id.upload_image_button)
        buttonSubmit = findViewById(R.id.buttonSubmit)
        ivUploadedPhoto = findViewById(R.id.upload_image_button)

        // Initialize FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Request permissions for location if not granted
        checkLocationPermission()

        // Populate spinner with animal types
        val animalTypes = listOf("Dog", "Cat", "Bird", "Other")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, animalTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerAnimalType.adapter = adapter

        // Set click listeners
        buttonPickLocation.setOnClickListener { getLastLocation() }

        buttonUploadPhoto.setOnClickListener { showPhotoOptions() }

        buttonSubmit.setOnClickListener { validateAndSubmitForm() }
    }

    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST_CODE)
        } else {
            getLastLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                val latitude = location.latitude
                val longitude = location.longitude
                editTextLocation.setText("Lat: $latitude, Lon: $longitude")
            } else {
                Toast.makeText(this, "Unable to retrieve location", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showPhotoOptions() {
        val options = arrayOf("Take Photo", "Choose from Gallery")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Upload Photo")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> openCamera()
                    1 -> openGallery()
                }
            }
            .show()
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                IMAGE_REQUEST_CODE -> {
                    val imageUri = data?.data
                    ivUploadedPhoto.setImageURI(imageUri)
                    ivUploadedPhoto.visibility = View.VISIBLE
                }
                CAMERA_REQUEST_CODE -> {
                    val photo = data?.extras?.get("data") as Bitmap
                    ivUploadedPhoto.setImageBitmap(photo)
                    ivUploadedPhoto.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun validateAndSubmitForm() {
        val animalType = spinnerAnimalType.selectedItem.toString()
        val conditionId = radioGroupCondition.checkedRadioButtonId
        val condition = findViewById<RadioButton>(conditionId)?.text.toString()
        val location = editTextLocation.text.toString()
        val description = editTextDescription.text.toString()
        val name = editTextName.text.toString()
        val contact = editTextContact.text.toString()

        if (animalType.isBlank() || condition.isBlank() || location.isBlank() || description.isBlank() || name.isBlank() || contact.isBlank()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Form submitted successfully", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getLastLocation()
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }
}
