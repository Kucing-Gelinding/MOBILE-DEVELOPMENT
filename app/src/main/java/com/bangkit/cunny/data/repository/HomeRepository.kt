package com.bangkit.cunny.data.repository

import androidx.lifecycle.LiveData
import com.bangkit.cunny.data.database.BookmarkDao
import com.bangkit.cunny.data.database.BookmarkModel

class HomeRepository(private val bookmarkDao: BookmarkDao) {
    val bookmarks: LiveData<List<BookmarkModel>> = bookmarkDao.getAllBookmarks()
}