package com.bangkit.cunny.ui.onboarding.screens

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bangkit.cunny.R
import com.bangkit.cunny.databinding.FragmentFirstScreenBinding
import com.bangkit.cunny.databinding.FragmentSecondScreenBinding


class SecondScreen : Fragment() {

    private lateinit var binding: FragmentSecondScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Gunakan ViewBinding untuk menghubungkan layout
        binding = FragmentSecondScreenBinding.inflate(inflater, container, false)

        // Set click listener pada buttonContinue
        binding.buttonContinue.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_create_profile_fragment)
            onBoardingFinished()
        }

        playAnimation()

        return binding.root
    }

    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.cunny, View.TRANSLATION_X, 60f, -60f).apply {
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