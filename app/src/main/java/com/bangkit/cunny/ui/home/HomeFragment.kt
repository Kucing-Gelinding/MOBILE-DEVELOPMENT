package com.bangkit.cunny.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.cunny.data.model.LearningMaterialModel
import com.bangkit.cunny.databinding.FragmentHomeBinding
import com.bangkit.cunny.helper.BookmarkAdapter
import com.bangkit.cunny.ui.bookmark.BookmarkViewModel
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

        val viewModel = ViewModelProvider(this)[BookmarkViewModel::class.java]
        val adapter = BookmarkAdapter() // Buat adapter untuk menampilkan data

        // Set an onClick listener for a button (you can change it to any view component)
        binding.profileIcon.setOnClickListener {
            // Intent to open ProfileActivity
            val intent = Intent(activity, ProfileActivity::class.java)
            startActivity(intent)
        }

        // Observe isLoading LiveData to show/hide ProgressBar
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        binding.rvBookmark.layoutManager = LinearLayoutManager(requireContext())
        binding.rvBookmark.adapter = adapter

        // Observe bookmarks data and update UI accordingly
        viewModel.bookmarks.observe(viewLifecycleOwner) { bookmarks ->
            if (bookmarks.isEmpty()) {
                // Show the emptyImage when there are no bookmarks
                binding.emptyImage.visibility = View.VISIBLE
                binding.rvBookmark.visibility = View.GONE
            } else {
                // Hide the emptyImage and show the RecyclerView when bookmarks are available
                binding.emptyImage.visibility = View.GONE
                binding.rvBookmark.visibility = View.VISIBLE
                adapter.submitList(bookmarks)
            }
        }

        // Tambahkan callback untuk klik item
        adapter.setOnItemClickCallback { bookmark ->
            val learningMaterial = LearningMaterialModel(
                id = bookmark.id,
                title = bookmark.title,
                description = bookmark.description,
                subMaterial = bookmark.subMaterials, // Add relevant data or empty
                learningImagePath = bookmark.learningImagePath
            )
            val intent = Intent(requireContext(), SubMaterialsActivity::class.java).apply {
                putExtra("SubMaterial", learningMaterial)
            }
            startActivity(intent)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
