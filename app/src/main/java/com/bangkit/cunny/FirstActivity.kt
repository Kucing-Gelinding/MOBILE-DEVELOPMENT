package com.bangkit.cunny

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.bangkit.cunny.databinding.ActivityFirstBinding
import com.bangkit.cunny.ui.authen.LoginActivity
import com.bangkit.cunny.ui.authen.RegisterActivity

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val navController = findNavController(R.id.nav_host_fragment_activity_first)

        // Handle navigation from SplashFragment to MainActivity
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.viewPagerFragment) {
                val isOnboardingCompleted = onBoardingFinished() // Check onboarding status
                if (isOnboardingCompleted) {
                    val intent = Intent(this, RegisterActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun onBoardingFinished(): Boolean {
        val sharedPref = getSharedPreferences("onboarding", MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }
}
