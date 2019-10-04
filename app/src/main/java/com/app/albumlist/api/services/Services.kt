package com.app.albumlist.api.services

import com.app.albumlist.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Services {

    val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getAlbumService() : AlbumService {
        return retrofit.create(AlbumService::class.java)
    }
}