package com.bangkit.cunny.ui.authen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bangkit.cunny.R

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // Find the button and set a click listener
        val loginButton = view.findViewById<Button>(R.id.buttonLogin)
        loginButton.setOnClickListener {
            // Navigate to the home fragment
            findNavController().navigate(R.id.action_loginFragment_to_navigation_home)
        }

        return view
    }
}