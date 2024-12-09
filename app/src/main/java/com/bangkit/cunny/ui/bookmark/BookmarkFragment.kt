package com.bangkit.cunny.ui.bookmark

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
import com.bangkit.cunny.data.repository.BookmarkRepository
import com.bangkit.cunny.databinding.FragmentBookmarkBinding
import com.bangkit.cunny.helper.BookmarkAdapter
import com.bangkit.cunny.ui.sub_materials.SubMaterialsActivity

class BookmarkFragment : Fragment() {

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)

        // Inisialisasi database dan repository
        val database = BookmarkRoomDatabase.getDatabase(requireContext())
        val repository = BookmarkRepository(database.bookmarkDao())
        val factory = BookmarkViewModelFactory(repository)

        // Inisialisasi ViewModel dengan Factory
        val viewModel = ViewModelProvider(this, factory)[BookmarkViewModel::class.java]
        val adapter = BookmarkAdapter()

        binding.rvBookmark.layoutManager = LinearLayoutManager(requireContext())
        binding.rvBookmark.adapter = adapter

        // Observe isLoading LiveData untuk ProgressBar
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        // Observe bookmarks LiveData untuk memperbarui UI
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

        // Tambahkan callback untuk klik item
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

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}