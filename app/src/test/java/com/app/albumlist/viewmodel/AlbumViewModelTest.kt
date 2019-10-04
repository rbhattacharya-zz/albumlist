package com.app.albumlist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import com.app.albumlist.LiveDataTestUtil
import com.app.albumlist.MainCoroutineRule
import com.app.albumlist.model.FakeAlbumRepository
import com.app.albumlist.model.entity.AlbumData
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Unit tests for the implementation of [AlbumViewModel]
 */
@ExperimentalCoroutinesApi
class AlbumViewModelTest {
    // Subject under test
    private lateinit var albumViewModel: AlbumViewModel

    // Use a fake repository to be injected into the viewmodel
    private lateinit var fakeAlbumRepository: FakeAlbumRepository

    //val context: Application = ApplicationProvider.getApplicationContext()

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
        fakeAlbumRepository = FakeAlbumRepository()
        val album1 = AlbumData(1, "Title 1", 1)
        val album2 = AlbumData(2, "Title 2", 2)
        val album3 = AlbumData(3, "Title 3", 1)
        fakeAlbumRepository.addAlbums(album1, album2, album3)

        albumViewModel = AlbumViewModel(fakeAlbumRepository)
    }

    @Test
    fun getAlbumsFromRepositoryAndLoadIntoView() {
        Truth.assertThat(LiveDataTestUtil.getValue(albumViewModel.allAlbumsData)).hasSize(3)
    }
}