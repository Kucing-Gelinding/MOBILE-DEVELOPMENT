package com.bangkit.cunny.ui.materials

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.cunny.R
import com.bangkit.cunny.data.Material
import com.bangkit.cunny.data.response.LearningMaterials
import com.bangkit.cunny.databinding.FragmentMaterialBinding
import com.bangkit.cunny.di.Injection
import com.bangkit.cunny.helper.MaterialAdapter

class MaterialFragment : Fragment() {
    private var _binding: FragmentMaterialBinding? = null
    private val binding get() = _binding!!

    private lateinit var materialViewModel: MaterialViewModel
    private val materialAdapter = MaterialAdapter(ArrayList())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMaterialBinding.inflate(inflater, container, false)

        // Inisialisasi ViewModel
        val factory = MaterialViewModelFactory(Injection.provideRepository(requireContext()))
        materialViewModel = ViewModelProvider(this, factory)[MaterialViewModel::class.java]

        // Setup RecyclerView
        binding.rvMaterial.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMaterial.adapter = materialAdapter

        // Observasi LiveData dari ViewModel
        observeViewModel()

        // Fetch data dari API
        materialViewModel.fetchMaterials()

        return binding.root
    }

    private fun observeViewModel() {
        materialViewModel.materials.observe(viewLifecycleOwner) { response ->
            response?.learningMaterials?.let { materials ->
                updateMaterialList(materials)
            }
        }

        materialViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        materialViewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateMaterialList(materials: LearningMaterials) {
        // Menangani subMaterials jika ada
        val materialList = materials.subMaterials?.map { subMaterial ->
            Material(
                name = materials.title ?: "No Title",
                description = materials.description ?: "No Description",
                photo = R.drawable.ic_place_holder // Ganti dengan placeholder image
            )
        } ?: listOf()  // Menangani kondisi jika subMaterials null
        materialAdapter.updateData(ArrayList(materialList))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
