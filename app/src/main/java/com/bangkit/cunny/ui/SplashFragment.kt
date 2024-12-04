package com.bangkit.cunny.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bangkit.cunny.R
import com.bangkit.cunny.ui.authen.RegisterActivity


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        Handler(Looper.getMainLooper()).postDelayed({

            if(onBoardingFinished()){
                val intent = Intent(requireContext(), RegisterActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }else{
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
            }

        }, 3000)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    private fun onBoardingFinished(): Boolean{
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)

        return sharedPref.getBoolean("Finished", false)
    }
}