package com.bangkit.cunny.ui.materials_detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.cunny.R
import com.bangkit.cunny.data.model.LearningMaterialModel
import com.bangkit.cunny.data.model.SubMaterialModel
import com.bangkit.cunny.databinding.ActivityDetailBinding
import com.bangkit.cunny.ui.practice.PracticeActivity
import com.bumptech.glide.Glide
import com.bangkit.cunny.helper.PreferencesHelper

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var preferencesHelper: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferencesHelper = PreferencesHelper(this)

        val myObject = intent.getParcelableExtra<SubMaterialModel>("MaterialDetail")
        myObject?.let {
            // Tambahkan material ke list recent di SharedPreferences
            preferencesHelper.saveRecentMaterial(it)

            // Tampilkan data di UI
            Glide.with(this)
                .load(it.learningImagePath)
                .into(binding.tvImg)
            binding.tvName.text = it.subMaterial
            binding.tvDescription.text = it.subBodyMaterial
        }

        binding.buttonStartPractice.setOnClickListener {
            val intent = Intent(this, PracticeActivity::class.java)
            startActivity(intent)
        }
    }
}

