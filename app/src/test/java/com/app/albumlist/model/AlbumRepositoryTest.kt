package com.app.albumlist.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.albumlist.LiveDataTestUtil
import com.app.albumlist.MainCoroutineRule
import com.app.albumlist.model.entity.AlbumData
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AlbumRepositoryTest {

    private lateinit var albumRepository: AlbumRepository

    // Use a fake repository to be injected into the viewmodel
    private lateinit var fakeAlbumDao: FakeAlbumDao

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        // We initialise the tasks to 3, with one active and two completed
        fakeAlbumDao = FakeAlbumDao()

        albumRepository = AlbumRepository()
        val album1 = AlbumData(1, "Title 1", 1)
        val album2 = AlbumData(2, "Title 2", 2)
        val album3 = AlbumData(3, "Title 3", 1)
        fakeAlbumDao.addAlbums(album1, album2, album3)
        albumRepository.setAlbumDao(fakeAlbumDao)
    }

    @Test
    fun getAlbumsFromRepository() {
        Truth.assertThat(LiveDataTestUtil.getValue(albumRepository.getAlbums(MainScope()))).hasSize(3)
    }

    @Test
    fun addAlbumToRepository() {
        val album4 = AlbumData(4, "Title 4", 2)
        val album5 = AlbumData(5, "Title 5", 3)
        val album6 = AlbumData(6, "Title 6", 1)

        runBlocking { albumRepository.insertAll(listOf(album4, album5, album6)) }
        Truth.assertThat(LiveDataTestUtil.getValue(albumRepository.getAlbums(MainScope()))).hasSize(6)
    }


}