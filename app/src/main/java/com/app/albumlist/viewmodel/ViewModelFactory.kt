package com.app.albumlist.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.albumlist.model.AlbumRepository
import com.app.albumlist.model.AlbumRoomDatabase
import kotlinx.coroutines.GlobalScope

class ViewModelFactory(val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass){
            if(isAssignableFrom(AlbumViewModel::class.java)){
                val albumsDao = AlbumRoomDatabase.getDatabase(application, GlobalScope).albumDao()
                val repository = AlbumRepository()
                repository.setAlbumDao(albumsDao)
                return AlbumViewModel(repository) as T
            }
        } as T
}