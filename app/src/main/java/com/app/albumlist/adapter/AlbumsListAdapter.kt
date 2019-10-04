package com.app.albumlist.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.albumlist.R
import com.app.albumlist.model.entity.AlbumData

class AlbumsListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<AlbumsListAdapter.AlbumViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var albums = emptyList<AlbumData>() // Cached copy of albums

    inner class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val albumItemView: TextView = itemView.findViewById(R.id.title)
        val itemLayout: LinearLayout = itemView.findViewById(R.id.item_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val itemView = inflater.inflate(R.layout.album_row_item, parent, false)
        return AlbumViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val current = albums[position]
        holder.albumItemView.text = current.title
        if(position %2 == 1) {
            holder.itemLayout.setBackgroundColor(Color.parseColor("#FFFFFF"))
        } else {
            holder.itemLayout.setBackgroundColor(Color.parseColor("#FFFAF8FD"))
        }
    }

    internal fun setAlbums(albums: List<AlbumData>) {
        this.albums = albums
        notifyDataSetChanged()
    }

    override fun getItemCount() = albums.size
}