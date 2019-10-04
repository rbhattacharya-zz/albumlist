package com.app.albumlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.albumlist.model.AlbumRepository
import com.app.albumlist.model.entity.AlbumData

class AlbumViewModel(repository: AlbumRepository) : ViewModel() {

    //private val repository: AlbumRepository
    val allAlbumsData: LiveData<List<AlbumData>>

    init {
        //val albumsDao = AlbumRoomDatabase.getDatabase(application, viewModelScope).albumDao()
        //repository.setAlbumDao(albumsDao)
        allAlbumsData = repository.getAlbums(viewModelScope)
    }

}