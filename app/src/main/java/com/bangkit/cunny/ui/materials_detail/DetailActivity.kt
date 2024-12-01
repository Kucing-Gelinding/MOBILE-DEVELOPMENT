package com.bangkit.cunny.ui.materials_detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkit.cunny.R

class DetailActivity : AppCompatActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.hide()

        val playerPost = intent.getIntExtra("material_position", 0)
        val dataNames = resources.getStringArray(R.array.data_name)
        val dataDescriptions = resources.getStringArray(R.array.data_description)
        val dataPhotos = resources.obtainTypedArray(R.array.data_photo)

        val tvImageView: ImageView = findViewById(R.id.tv_img)
        tvImageView.setImageResource(dataPhotos.getResourceId(playerPost, -1))

        val tvPlayerName: TextView = findViewById(R.id.tv_name)
        tvPlayerName.text = dataNames[playerPost]

        val tvPlayerDescription: TextView = findViewById(R.id.tv_description)
        tvPlayerDescription.text = dataDescriptions[playerPost]


        // Recycle typed array to avoid memory leaks
        dataPhotos.recycle()
    }

}