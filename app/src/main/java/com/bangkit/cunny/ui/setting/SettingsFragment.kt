package com.bangkit.cunny.ui.setting

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.bangkit.cunny.databinding.FragmentSettingsBinding
import com.bangkit.cunny.ui.authen.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Observing logout status
        observeLogoutStatus()

        // Listener for logoutOption
        binding.logoutOption.setOnClickListener {
            signOut()
        }

        // Listener for aboutUs
        binding.aboutUs.setOnClickListener {
            openWebPage("https://github.com/Kucing-Gelinding/Cunny")
        }

        // Listener for helpOption
        binding.helpOption.setOnClickListener {
            openWebPage("https://github.com/Kucing-Gelinding/Cunny")
        }

        return root
    }

    private fun observeLogoutStatus() {
        viewModel.logoutStatus.observe(viewLifecycleOwner) { isLoggedOut ->
            if (isLoggedOut == true) {
                // Navigate to LoginActivity
                startActivity(Intent(requireContext(), LoginActivity::class.java))
                requireActivity().finish()
            } else {
                Toast.makeText(requireContext(), "Failed to log out. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signOut() {
        lifecycleScope.launch {
            try {
                val credentialManager = CredentialManager.create(requireContext())
                viewModel.logout(auth, credentialManager)
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(requireContext(), "Logout failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openWebPage(url: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = android.net.Uri.parse(url)
        }
        if (intent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(
                requireContext(),
                "No application can handle this request",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}