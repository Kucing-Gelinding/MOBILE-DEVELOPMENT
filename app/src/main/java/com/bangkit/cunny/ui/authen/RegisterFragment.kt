package com.bangkit.cunny.ui.authen

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bangkit.cunny.R
import com.bangkit.cunny.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Gunakan ViewBinding untuk menghubungkan layout
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        // Set click listener pada tombol register
        binding.registerButton.setOnClickListener {
            // Navigate ke login fragment
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        playAnimation()

        return binding.root
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.cunny, View.TRANSLATION_Y, 60f, -60f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(100)
        val nameTextView =
            ObjectAnimator.ofFloat(binding.nameTextView, View.ALPHA, 1f).setDuration(100)
        val edRegisterName =
            ObjectAnimator.ofFloat(binding.edRegisterName, View.ALPHA, 1f).setDuration(100)
        val emailTextView =
            ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f).setDuration(100)
        val edRegisterEmail =
            ObjectAnimator.ofFloat(binding.edRegisterEmail, View.ALPHA, 1f).setDuration(100)
        val passwordTextView =
            ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(100)
        val edRegisterPassword =
            ObjectAnimator.ofFloat(binding.edRegisterPassword, View.ALPHA, 1f).setDuration(100)
        val signup = ObjectAnimator.ofFloat(binding.registerButton, View.ALPHA, 1f).setDuration(100)

        AnimatorSet().apply {
            playSequentially(
                title,
                nameTextView,
                edRegisterName,
                emailTextView,
                edRegisterEmail,
                passwordTextView,
                edRegisterPassword,
                signup
            )
            startDelay = 100
        }.start()
    }
}