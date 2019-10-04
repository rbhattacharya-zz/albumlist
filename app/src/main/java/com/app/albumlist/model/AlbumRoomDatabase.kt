package com.app.albumlist.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.albumlist.model.dao.AlbumDao
import com.app.albumlist.model.entity.AlbumData
import kotlinx.coroutines.CoroutineScope

@Database(entities = arrayOf(AlbumData::class), version = 1)
public abstract class AlbumRoomDatabase : RoomDatabase() {

    abstract fun albumDao(): AlbumDao

    private class AlbumDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

    }

    companion object {
        @Volatile
        private var INSTANCE: AlbumRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AlbumRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AlbumRoomDatabase::class.java,
                    "album_database"
                )
                    .addCallback(
                        AlbumDatabaseCallback(
                            scope
                        )
                    )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}