package com.bangkit.cunny.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.cunny.data.database.BookmarkModel
import com.bangkit.cunny.data.repository.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    // LiveData untuk loading state
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    // LiveData untuk bookmarks
    val bookmarks: LiveData<List<BookmarkModel>> = repository.bookmarks

    init {
        fetchBookmarks()
    }

    private fun fetchBookmarks() {
        _isLoading.value = true
        viewModelScope.launch {
            // Simulate data fetching if needed
            _isLoading.postValue(false)
        }
    }
}