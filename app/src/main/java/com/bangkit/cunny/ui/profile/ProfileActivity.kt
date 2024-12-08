package com.bangkit.cunny.ui.profile

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkit.cunny.R
import com.bangkit.cunny.databinding.ActivityProfileBinding
import com.bangkit.cunny.databinding.ActivityRegisterBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        playAnimation()
    }

    private fun playAnimation() {

        val topBackground =
            ObjectAnimator.ofFloat(binding.topBackground, View.ALPHA, 1f).setDuration(100)
        val profileImage =
            ObjectAnimator.ofFloat(binding.profileImage, View.ALPHA, 1f).setDuration(100)
        val changeImageProfile =
            ObjectAnimator.ofFloat(binding.changeImageProfile, View.ALPHA, 1f).setDuration(100)


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
        val registerBtn =
            ObjectAnimator.ofFloat(binding.registerButton, View.ALPHA, 1f).setDuration(100)

        AnimatorSet().apply {
            playSequentially(
                topBackground,
                profileImage,
                changeImageProfile,

                nameTextView,
                edRegisterName,
                emailTextView,
                edRegisterEmail,
                passwordTextView,
                edRegisterPassword,
                registerBtn,
            )
            startDelay = 100
        }.start()
    }
}