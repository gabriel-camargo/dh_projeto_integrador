package com.gabrielcamargo.projetointegrador.search.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.model.MovieModel

class SearchAdapter(private var movies: MutableList<MovieModel>, private val listener: (MovieModel) -> Unit): RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_movie, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = movies[position]

        holder.bind(item)
        holder.itemView.setOnClickListener { listener(movies[position]) }
    }

    override fun getItemCount() = movies.size
}