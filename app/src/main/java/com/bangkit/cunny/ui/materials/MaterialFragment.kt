package com.bangkit.cunny.ui.materials

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.cunny.R
import com.bangkit.cunny.data.Material
import com.bangkit.cunny.databinding.FragmentMaterialBinding
import com.bangkit.cunny.helper.MaterialAdapter
import com.bangkit.cunny.ui.materials_detail.DetailActivity

class MaterialFragment : Fragment() {
    private lateinit var rvMaterial: RecyclerView
    private val list = ArrayList<Material>()


    private var _binding: FragmentMaterialBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMaterialBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Akses RecyclerView langsung melalui binding
        rvMaterial = binding.rvMaterial
        rvMaterial.setHasFixedSize(true)

        list.addAll(getListMaterial())
        showRecyclerList()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getListMaterial(): ArrayList<Material> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)

        val listPlayer = ArrayList<Material>()
        val minSize = listOf(dataName.size,  dataDescription.size, dataPhoto.length()).minOrNull() ?: 0

        for (i in 0 until minSize) {
            val player = Material(
                dataName[i],
                dataDescription[i],
                dataPhoto.getResourceId(i, -1)
            )
            listPlayer.add(player)
        }
        dataPhoto.recycle()

        return listPlayer
    }

    private fun showRecyclerList() {
        rvMaterial.layoutManager = LinearLayoutManager(requireContext())
        val playerAdapter = MaterialAdapter(list)
        rvMaterial.adapter = playerAdapter

        playerAdapter.setOnItemClickCallback(object : MaterialAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Material, position: Int) {
                showSelectedMaterial(position)
            }
        })
    }

    private fun showSelectedMaterial(position: Int) {
        val intent = Intent(requireContext(), DetailActivity::class.java)
        intent.putExtra("material_position", position)
        startActivity(intent)
    }
}