package com.gabrielcamargo.projetointegrador.utils.moviesoffline.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.utils.moviesoffline.model.MovieModelOffline

class MovieOfflineAdapter(
    private val dataSet: List<MovieModelOffline>,
    private val clickListener: (MovieModelOffline) -> Unit
): RecyclerView.Adapter<MovieOfflineViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieOfflineViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        return MovieOfflineViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieOfflineViewHolder, position: Int) {
        holder.bind(dataSet[position])
        holder.itemView.setOnClickListener{clickListener(dataSet[position])}    }

    override fun getItemCount() = dataSet.size
}