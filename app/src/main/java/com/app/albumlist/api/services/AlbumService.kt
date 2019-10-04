package com.app.albumlist.api.services

import com.app.albumlist.model.entity.AlbumData
import retrofit2.Call
import retrofit2.http.GET

interface AlbumService {
    @GET("albums")
    fun getAlbumsList(): Call<ArrayList<AlbumData>>
}