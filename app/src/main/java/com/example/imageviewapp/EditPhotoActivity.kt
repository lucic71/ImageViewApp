package com.example.imageviewapp

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import com.example.imageviewapp.databinding.ActivityEditPhotoBinding
import iamutkarshtiwari.github.io.ananas.editimage.EditImageActivity
import iamutkarshtiwari.github.io.ananas.editimage.ImageEditorIntentBuilder
import misc.Misc
import java.io.File

class EditPhotoActivity : AppCompatActivity() {

    /* Request codes. */

    private val galleryFetchRequest = 0
    private val photoEditorRequest  = 1

    /* DataBinding and elements from XML. */

    private lateinit var binding: ActivityEditPhotoBinding
    private var photoView: ImageView? = null

    /* Test file for saved image. This is just a POC. */

    private val filePathSaved: String = "/storage/1E0A-1A08/Pictures/test.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {

        /* Initialize the activity. */
        
        super.onCreate(savedInstanceState)

        binding = ActivityEditPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        photoView = binding.imageView

        /* Fetch a photo from gallery. */

        val galleryIntent = Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, galleryFetchRequest)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {

        super.onActivityResult(requestCode, resultCode, intent)

        /*
            Handler for gallery intent. Using the fetched URI, it will trigger
            the photo editor.
         */

        if (resultCode == Activity.RESULT_OK && requestCode == galleryFetchRequest) {

            val imageUri = intent?.data
            val filePath = imageUri?.let { Misc.uriToPath(applicationContext, it) }

            val editorIntent = ImageEditorIntentBuilder(this, filePath, filePathSaved)
                .withAddText()
                .withPaintFeature()
                .withFilterFeature()
                .withRotateFeature()
                .withCropFeature()
                .withBrightnessFeature()
                .withSaturationFeature()
                .withBeautyFeature()
                .withStickerFeature()
                .forcePortrait(true)
                .build()

            EditImageActivity.start(this, editorIntent, photoEditorRequest)

        }

        /*
            Handler for photo editor intent. After saving the photo, it will display
            it in an ImageView.
         */

        else if (requestCode == photoEditorRequest && resultCode == Activity.RESULT_OK) {

            val imgFile = File(filePathSaved)

            if (imgFile.exists()) {

                val imgBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
                photoView?.setImageBitmap(imgBitmap)

            }


        }

    }

}

