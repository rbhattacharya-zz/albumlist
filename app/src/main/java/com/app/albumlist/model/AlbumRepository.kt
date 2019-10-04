package com.app.albumlist.model

import androidx.lifecycle.LiveData
import com.app.albumlist.api.services.Services
import com.app.albumlist.model.dao.AlbumDao
import com.app.albumlist.model.entity.AlbumData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class AlbumRepository {

    private lateinit var albumDao: AlbumDao

    fun setAlbumDao(albumDao: AlbumDao){
        this.albumDao = albumDao
    }

    suspend fun insertAll(albumsListData: List<AlbumData>) {
        albumDao.insert(albumsListData)
    }

    open fun getAlbums(scope: CoroutineScope): LiveData<List<AlbumData>> {
        refreshAlbumData(scope)
        return albumDao.getAllAlbums()

    }

    private fun refreshAlbumData(scope: CoroutineScope) {
        Services.getAlbumService().getAlbumsList().enqueue(object : Callback<ArrayList<AlbumData>> {
            override fun onResponse(call: Call<ArrayList<AlbumData>>, response: Response<ArrayList<AlbumData>>) {
                scope.launch { insertAll(response.body()) }
            }
            override fun onFailure(call: Call<ArrayList<AlbumData>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}