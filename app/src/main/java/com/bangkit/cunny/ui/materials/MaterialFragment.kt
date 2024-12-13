package com.bangkit.cunny.ui.materials

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.cunny.data.model.LearningMaterialModel
import com.bangkit.cunny.data.model.SubMaterialModel
import com.bangkit.cunny.data.response.LearningMaterial
import com.bangkit.cunny.databinding.FragmentMaterialBinding
import com.bangkit.cunny.di.Injection
import com.bangkit.cunny.helper.MaterialAdapter
import com.bangkit.cunny.ui.sub_materials.SubMaterialsActivity

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

        // Set the item click callback
        materialAdapter.setOnItemClickCallback(object : MaterialAdapter.OnItemClickCallback {
            override fun onItemClicked(data: LearningMaterialModel, position: Int) {
                // Handle item click (pass data or position)
                val intent = Intent(requireContext(), SubMaterialsActivity::class.java).apply {
                    putExtra("SubMaterial", data)
                }
                startActivity(intent)
            }
        })

        // Observasi LiveData dari ViewModel
        observeViewModel()

        // Fetch data from API
        materialViewModel.fetchMaterials()

        setupSearchView()

        return binding.root
    }

    private fun setupSearchView() {
        binding.searchView.setupWithSearchBar(binding.searchBar)

        binding.searchView.editText?.setOnEditorActionListener { textView, actionId, event ->
            val query = binding.searchView.text.toString()
            performSearch(query)
            binding.searchView.hide()
            false
        }

    }

    private fun performSearch(query: String) {
        materialViewModel.searchMaterials(query)
    }

    private fun observeViewModel() {
        materialViewModel.materials.observe(viewLifecycleOwner) { response ->
            response?.learningMaterials?.let { materials ->
                if (materials.isEmpty()) {
                    Toast.makeText(requireContext(), "No materials available", Toast.LENGTH_SHORT).show()
                } else {
                    updateMaterialList(materials)
                }
            } ?: run {
                Toast.makeText(requireContext(), "No materials available", Toast.LENGTH_SHORT).show()
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

    private fun updateMaterialList(materials: List<LearningMaterial>) {

        val material: MutableList<LearningMaterialModel> = ArrayList()
        materials.forEach { data ->
            val subMaterial: MutableList<SubMaterialModel> = ArrayList()
            for((i, v) in data.subMaterials!!.withIndex()){
                subMaterial.add(SubMaterialModel(i, v!!.get(0),data.subBodyMaterials!!.get(i)!!.get(0), data.learningImagePath))
            }
            material.add(LearningMaterialModel(data.id, data.title, data.description, subMaterial, data.learningImagePath))
        }

        materialAdapter.updateData(ArrayList(material))
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}