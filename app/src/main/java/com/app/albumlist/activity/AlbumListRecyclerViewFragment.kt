package com.app.albumlist.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.albumlist.R
import com.app.albumlist.adapter.AlbumsListAdapter
import com.app.albumlist.viewmodel.AlbumViewModel
import com.app.albumlist.viewmodel.ViewModelFactory

class AlbumListRecyclerViewFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(
            R.layout.fragment_album_list,
            container, false).apply{}

        recyclerView = rootView.findViewById(R.id.albums_list_recycler_view)

        // Set CustomAdapter as the adapter for RecyclerView.
        val adapter = AlbumsListAdapter(rootView.context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        val emptyView = rootView.findViewById<TextView>(R.id.empty_view)

        val albumViewModel = ViewModelProvider(this, ViewModelFactory(this.activity!!.application)).get(
            AlbumViewModel::class.java)
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

        return rootView
    }
}