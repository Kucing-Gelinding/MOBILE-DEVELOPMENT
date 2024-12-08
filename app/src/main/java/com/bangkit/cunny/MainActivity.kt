package com.bangkit.cunny

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bangkit.cunny.databinding.ActivityMainBinding
import com.bangkit.cunny.ui.authen.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_materials, R.id.navigation_favorite, R.id.navigation_settings
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Listener untuk memantau perubahan destinasi
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.navigation_home ||
                destination.id == R.id.navigation_materials ||
                destination.id == R.id.navigation_favorite ||
                destination.id == R.id.navigation_settings) {
                navView.visibility = View.VISIBLE
            } else {
                navView.visibility = View.GONE
            }
        }


//        auth = Firebase.auth
//        val firebaseUser = auth.currentUser
//        if (firebaseUser == null) {
//            // Not signed in, launch the Login activity
//            startActivity(Intent(this, LoginActivity::class.java))
//            finish()
//            return
//        }
    }
}