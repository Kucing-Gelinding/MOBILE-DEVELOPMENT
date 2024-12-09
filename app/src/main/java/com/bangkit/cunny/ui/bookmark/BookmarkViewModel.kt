package com.bangkit.cunny.ui.bookmark

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.cunny.data.database.BookmarkModel
import com.bangkit.cunny.data.database.BookmarkRoomDatabase
import com.bangkit.cunny.data.repository.BookmarkRepository
import kotlinx.coroutines.launch

class BookmarkViewModel(private val repository: BookmarkRepository) : ViewModel() {

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
