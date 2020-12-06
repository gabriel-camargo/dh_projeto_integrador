package com.gabrielcamargo.projetointegrador.utils.movies.view

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

class VerticalMovieListViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    private val txtName: TextView = view.findViewById<TextView>(R.id.txtName_itemMovie)
    private val txtGenre: TextView = view.findViewById<TextView>(R.id.txtGenre_itemMovie)
    private val txtStars: TextView = view.findViewById<TextView>(R.id.txtStars_itemMovie)
    private val txtYear: TextView = view.findViewById<TextView>(R.id.txtYear_itemMovie)
    private val imgView: ImageView = view.findViewById<ImageView>(R.id.img_itemMovie)
    private val cardCornerRadius = 12

    fun bind(movie: MovieModel) {
        txtName.text = movie.title
        txtGenre.text = "..."
        txtStars.text = 5.0.toString()
        txtYear.text = 2000.toString()

        Glide.with(view.context)
            .load(movie.getPathPoster())
            .transform(CenterCrop(), RoundedCorners(cardCornerRadius))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imgView)
    }
}