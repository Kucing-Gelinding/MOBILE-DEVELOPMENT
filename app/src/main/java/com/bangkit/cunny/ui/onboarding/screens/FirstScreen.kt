package com.bangkit.cunny.ui.onboarding.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bangkit.cunny.R

class FirstScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first_screen, container, false)

        // Find the TextView by its ID
        val continueButton = view.findViewById<Button>(R.id.buttonContinue)

        // Reference the ViewPager
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        // Set an OnClickListener on the TextView
        continueButton.setOnClickListener {
            viewPager?.currentItem = 1 // Navigate to the next page
        }

        return view
    }


}