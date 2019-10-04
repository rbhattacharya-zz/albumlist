package com.app.albumlist.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.albumlist.model.entity.AlbumData

@Dao
interface AlbumDao {
    @Query("SELECT * from album_table ORDER BY title ASC")
    fun getAllAlbums(): LiveData<List<AlbumData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(albumData: AlbumData)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(albumData: List<AlbumData>)

    @Query("DELETE FROM album_table")
    suspend fun deleteAll()
}