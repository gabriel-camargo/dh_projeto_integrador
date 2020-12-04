package com.gabrielcamargo.projetointegrador.favoritemovies.movielist.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.favoritemovies.movielist.model.MovieListModel
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class MovieListViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    private val txtName: TextView = view.findViewById(R.id.txtName_itemMovieList)
    private val movies: TextView = view.findViewById(R.id.txtCount_itemMovieList)
    private val img: ImageView = view.findViewById(R.id.img_itemMovieList)
    private val cardCornerRadius = 12

    fun bind(movieList: MovieListModel) {
        txtName.text = movieList.nome
        movies.text = movieList.qtd.toString()

        Glide.with(view.context)
            .load(movieList.img)
            .transform(CenterCrop(), RoundedCorners(cardCornerRadius))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(img)
    }
}
