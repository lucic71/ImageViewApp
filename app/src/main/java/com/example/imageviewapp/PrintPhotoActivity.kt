package com.example.imageviewapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class PrintPhotoActivity : AppCompatActivity() {

    private val galleryFetchRequest = 0
    private var photoView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_print_photo)

        photoView = findViewById(R.id.imageView)

        val galleryIntent = Intent(Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, galleryFetchRequest)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        if (resultCode == Activity.RESULT_OK && requestCode == galleryFetchRequest) {
            val fetchedImage = intent?.data as Uri
            photoView?.setImageURI(fetchedImage)
        }
    }

}

