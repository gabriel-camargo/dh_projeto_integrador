package com.gabrielcamargo.projetointegrador.favoritemovies.movielist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.favoritemovies.movielist.model.MovieListModel

class MovieListAdapter(
    private val dataSet: List<MovieListModel>,
    private val clickListener: (MovieListModel) -> Unit
): RecyclerView.Adapter<MovieListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)

        return MovieListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(dataSet[position])
        holder.itemView.setOnClickListener{clickListener(dataSet[position])}
    }

    override fun getItemCount() = dataSet.size
}