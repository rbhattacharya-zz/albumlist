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

        val recyclerView = findViewById<RecyclerView>(R.id.albums_list_recycler_view)
        val adapter = AlbumsListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val emptyView = findViewById<TextView>(R.id.empty_view)

        val albumViewModel = ViewModelProvider(this, ViewModelFactory(this.application)).get(AlbumViewModel::class.java)
        albumViewModel.allAlbumsData.observe(this, Observer { albums ->
            albums?.let {
                adapter.setAlbums(it)

                if(adapter.itemCount == 0){
                    recyclerView.visibility = View.GONE
                    emptyView.visibility = View.VISIBLE
                } else {
                    recyclerView.visibility = View.VISIBLE
                    emptyView.visibility = View.GONE
                }
            }
        })
    }
}
