package com.bangkit.cunny.ui.materials_detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.cunny.R
import com.bangkit.cunny.databinding.ActivityDetailBinding
import com.bangkit.cunny.ui.practice.PracticeActivity

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi View Binding
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        // Ambil data dari Intent
        val playerPost = intent.getIntExtra("material_position", 0)
        val dataNames = resources.getStringArray(R.array.data_name)
        val dataDescriptions = resources.getStringArray(R.array.data_description)
        val dataPhotos = resources.obtainTypedArray(R.array.data_photo)

        // Gunakan binding untuk mengakses View
        binding.tvImg.setImageResource(dataPhotos.getResourceId(playerPost, -1))
        binding.tvName.text = dataNames[playerPost]
        binding.tvDescription.text = dataDescriptions[playerPost]

        binding.buttonStartPractice.setOnClickListener {
            val intent = Intent(this, PracticeActivity::class.java)
            // Tambahkan data jika diperlukan untuk diteruskan ke PracticeActivity
            intent.putExtra("material_position", playerPost)
            startActivity(intent)
        }

        // Recycle typed array untuk menghindari memory leak
        dataPhotos.recycle()
    }
}
