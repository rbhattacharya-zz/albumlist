package com.app.albumlist.model

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.albumlist.model.dao.AlbumDao
import com.app.albumlist.model.entity.AlbumData

class FakeAlbumDao: AlbumDao {
    var albumDataList: ArrayList<AlbumData> = ArrayList()


    override fun getAllAlbums(): LiveData<List<AlbumData>> {
        val ret = MutableLiveData<List<AlbumData>>()
        ret.value = albumDataList
        return ret
    }

    override suspend fun insert(albumData: AlbumData) {
        albumDataList.add(albumData)
    }

    override suspend fun insert(albumData: List<AlbumData>) {
        albumDataList.addAll(albumData)
    }

    override suspend fun deleteAll() {
        albumDataList.clear()
    }

    @VisibleForTesting
    fun addAlbums(vararg albums: AlbumData) {
        for (album in albums) {
            albumDataList.add(album)
        }
    }
}