package com.bangkit.cunny.ui.practice

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bangkit.cunny.R
import com.bangkit.cunny.data.response.PredictionResult
import com.bangkit.cunny.data.retrofit.ApiConfig
import com.bangkit.cunny.databinding.ActivityPracticeBinding
import com.bangkit.cunny.ui.prediction.PredictionActivity
import com.bangkit.cunny.utils.getImageUri
import com.bangkit.cunny.utils.reduceFileImage
import com.bangkit.cunny.utils.uriToFile
import com.google.android.material.progressindicator.LinearProgressIndicator
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

class PracticeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeBinding
    private lateinit var progressIndicator: LinearProgressIndicator
    private var currentImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        savedInstanceState?.getString("imageUri")?.let { uriString ->
            currentImageUri = Uri.parse(uriString)
            showImage()
        }

        binding.buttonBack.setOnClickListener {
            finish()
        }

        supportActionBar?.hide()
        progressIndicator = findViewById(R.id.progressIndicator)
        val playerPost = intent.getIntExtra("material_position", 0)
        val dataDescriptions = resources.getString(R.string.practice)
        val dataPhotos = resources.obtainTypedArray(R.array.data_photo)

        val tvImageView: ImageView = findViewById(R.id.tv_img)
        tvImageView.setImageResource(dataPhotos.getResourceId(playerPost, -1))

        val tvPlayerDescription: TextView = findViewById(R.id.tv_name)
        tvPlayerDescription.text = dataDescriptions

        binding.galleryButton.setOnClickListener { startGallery() }
        binding.cameraButton.setOnClickListener { startCamera() }
        binding.buttonAdd.setOnClickListener { uploadImage() }
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun startCamera() {
        currentImageUri = getImageUri(this)
        launcherIntentCamera.launch(currentImageUri!!)
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            showImage()
        } else {
            currentImageUri = null
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.tvImg.setImageURI(it)
        }
    }

    private fun uploadImage() {
        currentImageUri?.let { uri ->
            val file = uriToFile(uri, this).reduceFileImage()
            val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "uploaded_file", file.name, requestImageFile
            )

            val apiService = ApiConfig.getApiServiceForImageUpload()
            // Tampilkan progress
            progressIndicator.visibility = View.VISIBLE
            lifecycleScope.launch {
                try {
                    val response = apiService.uploadImage(imageMultipart)
                    if (response.predictionResult != null) {
                        navigateToPrediction(response.predictionResult, uri)
                    } else {
                        Toast.makeText(this@PracticeActivity, "Failed: ${response.message}", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@PracticeActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                } finally {
                    // Sembunyikan progress
                    progressIndicator.visibility = View.GONE
                }
            }
        } ?: run {
            Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToPrediction(predictionResult: PredictionResult, imageUri: Uri) {
        val intent = Intent(this, PredictionActivity::class.java).apply {
            putExtra("image_uri", imageUri.toString())
            putExtra("predicted_label", predictionResult.predictedLabel)
            putExtra("predicted_class", predictionResult.predictedClass)
        }
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        currentImageUri?.let {
            outState.putString("imageUri", it.toString())
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getString("imageUri")?.let { uriString ->
            currentImageUri = Uri.parse(uriString)
            showImage()
        }
    }

}