package com.cgmdigitalhouse.cinelist.favoritemovies.movielist.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.cgmdigitalhouse.cinelist.R
import com.cgmdigitalhouse.cinelist.favoritemovies.movielist.model.MovieListModel
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieEntity

class MovieListViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    private val txtName: TextView = view.findViewById(R.id.txtName_itemMovieList)
    private val txtQtdMovies: TextView = view.findViewById(R.id.txtCount_itemMovieList)
    private val txtQtdTextMovies: TextView = view.findViewById(R.id.txtCountText_itemMovieList)
    private val movies: TextView = view.findViewById(R.id.txtCount_itemMovieList)
    private val img: ImageView = view.findViewById(R.id.img_itemMovieList)
    private val cardCornerRadius = 12

    fun bind(movieList: MovieListModel) {
        txtName.text = movieList.name

        when (movieList.qtd) {
            0 -> {
                txtQtdMovies.text =  view.context.getString(R.string.nenhum_filme)
                txtQtdTextMovies.text=""
            }
            1 -> {
                txtQtdMovies.text = movieList.qtd.toString()
                txtQtdTextMovies.text = view.context.getString(R.string.filme)
            }
            else -> {
                txtQtdMovies.text = movieList.qtd.toString()

            }
        }
//        movies.text = movieList.
//
//        Glide.with(view.context)
//            .load(movieList.img)
//            .transform(CenterCrop(), RoundedCorners(cardCornerRadius))
//            .transition(DrawableTransitionOptions.withCrossFade())
//            .into(img)
    }
}
