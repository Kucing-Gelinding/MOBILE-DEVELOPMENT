package com.bangkit.cunny.ui.bookmark

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.cunny.data.model.LearningMaterialModel
import com.bangkit.cunny.databinding.FragmentBookmarkBinding
import com.bangkit.cunny.helper.BookmarkAdapter
import com.bangkit.cunny.ui.sub_materials.SubMaterialsActivity

class BookmarkFragment : Fragment() {

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)

        val viewModel = ViewModelProvider(this)[BookmarkViewModel::class.java]
        val adapter = BookmarkAdapter() // Buat adapter untuk menampilkan data

        binding.rvBookmark.layoutManager = LinearLayoutManager(requireContext())
        binding.rvBookmark.adapter = adapter

        // Observe isLoading LiveData to show/hide ProgressBar
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

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
