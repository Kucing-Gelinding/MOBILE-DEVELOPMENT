package com.bangkit.cunny.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.cunny.data.database.BookmarkModel
import com.bangkit.cunny.data.database.BookmarkRoomDatabase

class HomeViewModel : ViewModel() {

    // LiveData to observe loading state
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        // Simulate fetching data
        fetchBookmarks()
    }

    private fun fetchBookmarks() {
        // Show loading state
        _isLoading.value = true

        // Fetch bookmarks in a background thread (for example, using Coroutine or AsyncTask)
        // You can replace this with the actual repository call
        // Simulate a delay for data loading
        Thread {
            Thread.sleep(2000)  // Simulating network or DB delay
            _isLoading.postValue(false)  // Hide loading after data is fetched
        }.start()
    }
}