package com.bangkit.cunny.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.cunny.data.repository.BookmarkRepository

class BookmarkViewModelFactory(private val repository: BookmarkRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookmarkViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookmarkViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}