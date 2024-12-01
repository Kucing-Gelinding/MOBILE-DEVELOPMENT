package com.bangkit.cunny.ui.onboarding.screens

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bangkit.cunny.R
import com.bangkit.cunny.databinding.FragmentFirstScreenBinding

class FirstScreen : Fragment() {

    private lateinit var binding: FragmentFirstScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Gunakan ViewBinding untuk menghubungkan layout
        binding = FragmentFirstScreenBinding.inflate(inflater, container, false)

        // Reference ViewPager dari activity
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        // Set click listener pada tombol continue
        binding.buttonContinue.setOnClickListener {
            viewPager?.currentItem = 1 // Pindah ke halaman berikutnya
        }

        playAnimation() // Pastikan binding sudah diinisialisasi sebelum memanggil

        return binding.root
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.cunny, View.TRANSLATION_X, -60f, 60f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val title = ObjectAnimator.ofFloat(binding.cunny, View.ALPHA, 1f).setDuration(100)
        val message =
            ObjectAnimator.ofFloat(binding.readTextView, View.ALPHA, 1f).setDuration(100)
        val emailTextView =
            ObjectAnimator.ofFloat(binding.onboardTextView, View.ALPHA, 1f).setDuration(100)
        val emailEditTextLayout =
            ObjectAnimator.ofFloat(binding.dotsIndicator, View.ALPHA, 1f).setDuration(100)
        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding.buttonContinue, View.ALPHA, 1f).setDuration(100)
        val login = ObjectAnimator.ofFloat(binding.skipTextView, View.ALPHA, 1f).setDuration(100)

        AnimatorSet().apply {
            playSequentially(
                title,
                message,
                emailTextView,
                emailEditTextLayout,
                passwordEditTextLayout,
                login
            )
            startDelay = 100
        }.start()
    }
}