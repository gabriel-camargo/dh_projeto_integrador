package com.cgmdigitalhouse.cinelist.utils.movies.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cgmdigitalhouse.cinelist.R
import com.cgmdigitalhouse.cinelist.utils.movies.model.MovieModel

class VerticalMovieListAdapter(
    private val dataset: MutableList<MovieModel>,
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

    fun removeAt(position: Int) {
        dataset.removeAt(position)
        notifyItemRemoved(position)
    }
}