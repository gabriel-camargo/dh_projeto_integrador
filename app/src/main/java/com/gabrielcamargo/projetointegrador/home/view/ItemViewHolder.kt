package com.gabrielcamargo.projetointegrador.home.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.utils.movies.model.MovieModel

class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val ivImage: ImageView = view.findViewById(R.id.ivImageCard)
    private val tvName: TextView = view.findViewById(R.id.tvNameCard)
    private val cardCornerRadius = 12

    fun bind(movieModel: MovieModel) {
        tvName.text = movieModel.title

        Glide.with(view.context)
            .load(movieModel.getPathPoster())
            .transform(CenterCrop(), RoundedCorners(cardCornerRadius))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(ivImage)
    }
}