package com.app.albumlist.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.albumlist.R
import com.app.albumlist.adapter.AlbumsListAdapter
import com.app.albumlist.viewmodel.AlbumViewModel
import com.app.albumlist.viewmodel.ViewModelFactory

class AlbumListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_list)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().run {
                replace(R.id.albums_list_recycler_view_fragment, AlbumListRecyclerViewFragment())
                commit()
            }
        }
    }
}
