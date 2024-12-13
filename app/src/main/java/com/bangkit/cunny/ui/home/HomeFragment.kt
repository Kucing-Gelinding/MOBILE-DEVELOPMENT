package com.bangkit.cunny.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.cunny.data.database.BookmarkRoomDatabase
import com.bangkit.cunny.data.model.LearningMaterialModel
import com.bangkit.cunny.data.repository.HomeRepository
import com.bangkit.cunny.databinding.FragmentHomeBinding
import com.bangkit.cunny.helper.BookmarkAdapter
import com.bangkit.cunny.helper.PreferencesHelper
import com.bangkit.cunny.helper.RecentMaterialsAdapter
import com.bangkit.cunny.ui.materials_detail.DetailActivity
import com.bangkit.cunny.ui.profile.ProfileActivity
import com.bangkit.cunny.ui.sub_materials.SubMaterialsActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Preference recent
        val preferencesHelper = PreferencesHelper(requireContext())
        val recentMaterials = preferencesHelper.getRecentMaterials()

        // Buat instance database dan repository
        val database = BookmarkRoomDatabase.getDatabase(requireContext())
        val repository = HomeRepository(database.bookmarkDao())
        val factory = HomeViewModelFactory(repository)

        // Inisialisasi ViewModel dengan Factory
        val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        val adapter = BookmarkAdapter()

        binding.profileIcon.setOnClickListener {
            startActivity(Intent(activity, ProfileActivity::class.java))
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        binding.rvBookmark.layoutManager = LinearLayoutManager(requireContext())
        binding.rvBookmark.adapter = adapter

        viewModel.bookmarks.observe(viewLifecycleOwner) { bookmarks ->
            if (bookmarks.isEmpty()) {
                binding.emptyImage.visibility = View.VISIBLE
                binding.rvBookmark.visibility = View.GONE
            } else {
                binding.emptyImage.visibility = View.GONE
                binding.rvBookmark.visibility = View.VISIBLE
                adapter.submitList(bookmarks)
            }
        }

        adapter.setOnItemClickCallback { bookmark ->
            val learningMaterial = LearningMaterialModel(
                id = bookmark.id,
                title = bookmark.title,
                description = bookmark.description,
                subMaterial = bookmark.subMaterials,
                learningImagePath = bookmark.learningImagePath
            )
            val intent = Intent(requireContext(), SubMaterialsActivity::class.java).apply {
                putExtra("SubMaterial", learningMaterial)
            }
            startActivity(intent)
        }

        if (recentMaterials.isNotEmpty()) {
            val recentAdapter = RecentMaterialsAdapter()
            binding.rvRecent.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.rvRecent.adapter = recentAdapter

            // Kirim list data ke adapter
            recentAdapter.setMaterials(recentMaterials)

            recentAdapter.setOnItemClickCallback { material ->
                val intent = Intent(requireContext(), DetailActivity::class.java).apply {
                    putExtra("MaterialDetail", material)
                }
                startActivity(intent)
            }
        } else {
            val recentAdapter = RecentMaterialsAdapter()
            binding.rvRecent.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.rvRecent.adapter = recentAdapter

            // Kirim placeholder ke adapter
            recentAdapter.setMaterials(emptyList())
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
