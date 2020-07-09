package com.example.imageviewapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fetchPhotoButton = findViewById<Button>(R.id.button)
        fetchPhotoButton.setOnClickListener { openPhotoActivity() }

    }

    private fun openPhotoActivity() {

        val intent = Intent(this, PrintPhotoActivity::class.java)
        startActivity(intent)

    }
}