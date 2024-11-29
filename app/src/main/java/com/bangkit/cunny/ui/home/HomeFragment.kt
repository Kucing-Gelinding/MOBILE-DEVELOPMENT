package com.bangkit.cunny.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bangkit.cunny.R
import com.bangkit.cunny.databinding.FragmentHomeBinding
import com.google.android.material.navigation.NavigationView

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var drawerLayout: DrawerLayout


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Setup DrawerLayout and Toolbar
        drawerLayout = binding.root.findViewById(R.id.drawer_layout)

        // Setup NavigationView to handle item selection
        val navigationView: NavigationView = binding.root.findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    // Handle navigation to Home
                }
                R.id.nav_favorite -> {
                    // Handle navigation to Favorite
                }
                R.id.nav_recent -> {
                    // Handle navigation to Recent Materials
                }
                R.id.nav_settings -> {
                    // Handle navigation to Settings
                }
                R.id.nav_about -> {
                    // Handle navigation to About
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
