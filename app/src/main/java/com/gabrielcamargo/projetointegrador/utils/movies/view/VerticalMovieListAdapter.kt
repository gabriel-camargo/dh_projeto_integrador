package com.gabrielcamargo.projetointegrador.utils.movies.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.utils.movies.model.MovieModel

class VerticalMovieListAdapter(
    private val dataset: List<MovieModel>,
    private val clickListener: (MovieModel) -> Unit
) : RecyclerView.Adapter<VerticalMovieListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalMovieListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        return VerticalMovieListViewHolder(view)
    }

    override fun onBindViewHolder(holder: VerticalMovieListViewHolder, position: Int) {
        holder.bind(dataset[position])
        holder.itemView.setOnClickListener { clickListener(dataset[position]) }
    }

    override fun getItemCount() = dataset.size

}