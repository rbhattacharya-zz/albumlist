package com.app.albumlist.model

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.albumlist.model.entity.AlbumData
import kotlinx.coroutines.CoroutineScope

class FakeAlbumRepository : AlbumRepository(){

    var albumData: ArrayList<AlbumData> = ArrayList()

    private var shouldReturnError = false

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override fun getAlbums(scope: CoroutineScope): LiveData<List<AlbumData>> {
        val ret = MutableLiveData<List<AlbumData>>()
        ret.value = albumData
        return ret
    }

    @VisibleForTesting
    fun addAlbums(vararg albums: AlbumData) {
        for (album in albums) {
            albumData.add(album)
        }
    }

}