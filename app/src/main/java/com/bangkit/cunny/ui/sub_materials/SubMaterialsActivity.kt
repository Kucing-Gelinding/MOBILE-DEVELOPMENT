package com.bangkit.cunny.ui.sub_materials

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.cunny.R
import com.bangkit.cunny.ui.materials_detail.DetailActivity

class SubMaterialsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_materials)

        supportActionBar?.hide()
        val playerPost = intent.getIntExtra("material_position", 0)
        val dataNames = resources.getStringArray(R.array.materiList)
        val dataDescriptions = resources.getStringArray(R.array.duration)
        val dataPhotos = resources.obtainTypedArray(R.array.data_photo)

        val tvImageView: ImageView = findViewById(R.id.tv_img)
        tvImageView.setImageResource(dataPhotos.getResourceId(playerPost, -1))

        val tvPlayerName: TextView = findViewById(R.id.tv_name)
        tvPlayerName.text = dataNames[playerPost]

        val tvPlayerDescription: TextView = findViewById(R.id.tv_duration)
        tvPlayerDescription.text = dataDescriptions[playerPost]

        // Menambahkan OnClickListener pada tv_name
        tvPlayerName.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("material_position", playerPost)
            }
            startActivity(intent)
        }

        // Recycle typed array to avoid memory leaks
        dataPhotos.recycle()
    }
}