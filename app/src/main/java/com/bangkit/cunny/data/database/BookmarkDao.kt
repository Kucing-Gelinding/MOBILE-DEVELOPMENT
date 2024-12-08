package com.bangkit.cunny.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bookmarkModel: BookmarkModel)

    @Delete
    fun delete(bookmarkModel: BookmarkModel)

    @Query("SELECT * from BookmarkModel ORDER BY id ASC")
    fun getAllBookmarks(): LiveData<List<BookmarkModel>>

    @Query("SELECT * FROM BookmarkModel WHERE id = :learningMaterialsId")
    fun getBookmarkById(learningMaterialsId: Int): BookmarkModel?

    @Query("SELECT * FROM BookmarkModel WHERE id = :learningMaterialsId")
    fun getBookmarkByIdLive(learningMaterialsId: Int): LiveData<BookmarkModel?>
}
