package com.bangkit.cunny.ui.setting

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.lifecycle.lifecycleScope
import com.bangkit.cunny.databinding.FragmentSettingsBinding
import com.bangkit.cunny.ui.authen.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Listener for logoutOption
        binding.logoutOption.setOnClickListener {
            signOut()
        }

        return root
    }

    private fun signOut() {
        lifecycleScope.launch {
            try {
                // Create CredentialManager instance
                val credentialManager = CredentialManager.create(requireContext())

                // Sign out from FirebaseAuth
                auth.signOut()

                // Clear saved credentials
                credentialManager.clearCredentialState(ClearCredentialStateRequest())

                // Navigate to LoginActivity
                startActivity(Intent(requireContext(), LoginActivity::class.java))
                requireActivity().finish()
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle any errors (e.g., show a Toast or Snackbar)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
