package com.app.albumlist.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "album_table")
data class AlbumData (@PrimaryKey
                      @SerializedName("id")
                      val id: Int,

                      @ColumnInfo(name = "title")
                      @SerializedName("title")
                      val title: String,

                      @SerializedName("userId")
                      val userId: Int)
