package com.bangkit.cunny.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.bangkit.cunny.R
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class ViewPagerFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var dotsIndicator: DotsIndicator
    private val viewModel: OnboardingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)

        viewPager = view.findViewById(R.id.viewPager)
        dotsIndicator = view.findViewById(R.id.dots_indicator)

        // Observasi data fragment dari ViewModel
        viewModel.fragments.observe(viewLifecycleOwner) { fragmentList ->
            val adapter = ViewPagerAdapter(
                ArrayList(fragmentList),
                requireActivity().supportFragmentManager,
                lifecycle
            )
            viewPager.adapter = adapter
            dotsIndicator.attachTo(viewPager)
        }

        return view
    }
}
