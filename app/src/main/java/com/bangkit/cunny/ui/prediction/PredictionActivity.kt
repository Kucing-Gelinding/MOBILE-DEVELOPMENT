package com.bangkit.cunny.ui.prediction

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkit.cunny.R
import com.bangkit.cunny.databinding.ActivityPredictionBinding

class PredictionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPredictionBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPredictionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBack.setOnClickListener {
            finish()
        }

        supportActionBar?.hide()
        val imageUri = intent.getStringExtra("image_uri")
        val predictedLabel = intent.getStringExtra("predicted_label")
        val predictedClass = intent.getIntExtra("predicted_class", -1)

        if (imageUri != null) {
            val uri = Uri.parse(imageUri)
        }

        binding.tvPredictionLabel.text = "Hasil dari scan kamu: $predictedLabel"
    }
}