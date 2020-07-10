package com.example.imageviewapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.imageviewapp.databinding.ActivityPrintPhotoBinding
import com.theartofdev.edmodo.cropper.CropImage
import com.zolad.zoominimageview.ZoomInImageView

class PrintPhotoActivity : AppCompatActivity() {

    private val galleryFetchRequest = 0
    private var photoView: ZoomInImageView? = null

    private lateinit var binding: ActivityPrintPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)

        binding = ActivityPrintPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        photoView = binding.imageView
        
        /* Fetch a photo from gallery. */

        val galleryIntent = Intent(Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, galleryFetchRequest)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {

        super.onActivityResult(requestCode, resultCode, intent)
        
        /* Handler for gallery intent. */

        if (resultCode == Activity.RESULT_OK && requestCode == galleryFetchRequest) {

            val result = intent?.data as Uri
            CropImage.activity(result).start(this)

        }
        
        /* Handler for crop image intent. */
        
        else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE &&
                resultCode == Activity.RESULT_OK) {

            val result = CropImage.getActivityResult(intent) as CropImage.ActivityResult
            photoView?.setImageURI(result.uri)

        }

    }

}

