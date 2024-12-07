package com.bangkit.cunny.ui.sub_materials

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.cunny.data.model.LearningMaterialModel
import com.bangkit.cunny.data.model.SubMaterialModel
import com.bangkit.cunny.data.response.LearningMaterial
import com.bangkit.cunny.databinding.ActivitySubMaterialsBinding
import com.bangkit.cunny.di.Injection
import com.bangkit.cunny.helper.SubMaterialAdapter
import com.bangkit.cunny.ui.materials.MaterialViewModel
import com.bangkit.cunny.ui.materials.MaterialViewModelFactory
import com.bangkit.cunny.ui.materials_detail.DetailActivity
import com.bumptech.glide.Glide

class SubMaterialsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySubMaterialsBinding
    private lateinit var materialViewModel: MaterialViewModel
    private val subMaterialAdapter = SubMaterialAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubMaterialsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val myObject = intent.getParcelableExtra<LearningMaterialModel>("SubMaterial")
        myObject?.let {
            Glide.with(this@SubMaterialsActivity)
                .load(myObject.learningImagePath)
                .into(binding.tvImg)
            updateMaterialList(myObject.subMaterial)
        }

        // Inisialisasi ViewModel
        val factory = MaterialViewModelFactory(Injection.provideRepository(this))
        materialViewModel = ViewModelProvider(this, factory)[MaterialViewModel::class.java]

        // Setup RecyclerView
        binding.rvChapterName.layoutManager = LinearLayoutManager(this)
        binding.rvChapterName.adapter = subMaterialAdapter


        // Set the item click callback
        subMaterialAdapter.setOnItemClickCallback(object : SubMaterialAdapter.OnItemClickCallback {
            override fun onItemClicked(data: SubMaterialModel, position: Int) {
                val intent = Intent(this@SubMaterialsActivity, DetailActivity::class.java).apply {
                    putExtra("MaterialDetail", data)
                }
                startActivity(intent)
            }
        })



        // Observasi LiveData dari ViewModel
        //observeViewModel()

        // Fetch data from API
        //materialViewModel.fetchMaterials()
    }

    private fun observeViewModel() {
        /*materialViewModel.materials.observe(this) { response ->
            response?.learningMaterials?.let { materials ->
                if (materials.isEmpty()) {
                    Toast.makeText(this, "No materials available", Toast.LENGTH_SHORT).show()
                } else {
                    updateMaterialList(materials)
                }
            } ?: run {
                Toast.makeText(this, "No materials available", Toast.LENGTH_SHORT).show()
            }
        }

        materialViewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        materialViewModel.error.observe(this) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }*/
    }

    private fun updateMaterialList(materials: List<SubMaterialModel>) {
        subMaterialAdapter.updateData(ArrayList(materials))
    }
}
