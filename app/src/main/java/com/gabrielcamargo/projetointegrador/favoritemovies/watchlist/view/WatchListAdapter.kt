package com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.model.MovieModel

class WatchListAdapter(
        private val dataSet: List<MovieModel>,
        private val clickListener: (MovieModel) -> Unit
): RecyclerView.Adapter<WatchListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchListViewHolder {
        val view: View =
                LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        return WatchListViewHolder(view)
    }

    override fun onBindViewHolder(holder: WatchListViewHolder, position: Int) {
        holder.bind(dataSet[position])
        holder.itemView.setOnClickListener{clickListener(dataSet[position])}    }

    override fun getItemCount() = dataSet.size
}
