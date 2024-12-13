package com.bangkit.cunny.ui.sub_materials

import android.content.Intent
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.cunny.data.database.BookmarkDao
import com.bangkit.cunny.data.database.BookmarkModel
import com.bangkit.cunny.data.database.BookmarkRoomDatabase
import com.bangkit.cunny.data.model.LearningMaterialModel
import com.bangkit.cunny.data.model.SubMaterialModel
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
    private lateinit var bookmarkDao: BookmarkDao
    private var isInitializing = false // Flag untuk mencegah listener saat inisialisasi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubMaterialsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val myObject = intent.getParcelableExtra<LearningMaterialModel>("SubMaterial")

        myObject?.let {
            binding.tvTitle.text = it.title
            Glide.with(this@SubMaterialsActivity)
                .load(myObject.learningImagePath)
                .into(binding.tvImg)
            updateMaterialList(myObject.subMaterial)
        }

        binding.backButton.setOnClickListener {
            finish()
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

        val database = BookmarkRoomDatabase.getDatabase(this)
        bookmarkDao = database.bookmarkDao()

        // Cek status bookmark dari database
        myObject?.let {
            Thread {
                val existingBookmark = bookmarkDao.getBookmarkById(it.id)
                runOnUiThread {
                    isInitializing = true // Atur flag ke true sebelum mengubah isChecked
                    binding.btnBookmark.isChecked = existingBookmark != null
                    isInitializing = false // Reset flag setelah selesai
                }
            }.start()
        }

        // Set listener untuk toggle bookmark
        binding.btnBookmark.setOnCheckedChangeListener(bookmarkCheckedChangeListener)
    }

    override fun onResume() {
        super.onResume()
        val myObject = intent.getParcelableExtra<LearningMaterialModel>("SubMaterial")
        myObject?.let {
            updateMaterialList(it.subMaterial)
        }
    }

    private val bookmarkCheckedChangeListener =
        CompoundButton.OnCheckedChangeListener { _, isChecked ->
            if (isInitializing) return@OnCheckedChangeListener // Abaikan perubahan selama inisialisasi

            val myObject = intent.getParcelableExtra<LearningMaterialModel>("SubMaterial")
            myObject?.let {
                val bookmark = BookmarkModel(
                    id = it.id,
                    title = it.title,
                    description = it.description,
                    subMaterials = it.subMaterial,
                    learningImagePath = it.learningImagePath
                )

                if (isChecked) {
                    Thread { bookmarkDao.insert(bookmark) }.start()
                    Toast.makeText(this, "Added to Bookmark", Toast.LENGTH_SHORT).show()
                } else {
                    Thread {
                        val existingBookmark = bookmarkDao.getBookmarkById(bookmark.id)
                        if (existingBookmark != null) {
                            bookmarkDao.delete(existingBookmark)
                        }
                    }.start()
                    Toast.makeText(this, "Removed from Bookmark", Toast.LENGTH_SHORT).show()
                }
            }
        }

    private fun updateMaterialList(materials: List<SubMaterialModel>) {
        subMaterialAdapter.updateData(ArrayList(materials))
    }
}
