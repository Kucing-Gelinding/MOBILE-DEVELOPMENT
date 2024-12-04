package com.bangkit.cunny.ui.onboarding

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.cunny.ui.onboarding.screens.FirstScreen
import com.bangkit.cunny.ui.onboarding.screens.SecondScreen

class OnboardingViewModel : ViewModel() {
    private val _fragments = MutableLiveData<List<Fragment>>()
    val fragments: LiveData<List<Fragment>> get() = _fragments

    init {
        _fragments.value = listOf(
            FirstScreen(),
            SecondScreen()
        )
    }
}