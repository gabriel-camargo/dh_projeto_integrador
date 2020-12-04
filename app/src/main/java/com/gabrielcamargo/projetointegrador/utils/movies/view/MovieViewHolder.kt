package com.gabrielcamargo.projetointegrador.utils.movies.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.utils.movies.model.MovieModel
import com.squareup.picasso.Picasso

class MovieViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val txtName: TextView = view.findViewById<TextView>(R.id.txtName_itemMovie)
    private val txtGenre: TextView = view.findViewById<TextView>(R.id.txtGenre_itemMovie)
    private val txtStars: TextView = view.findViewById<TextView>(R.id.txtStars_itemMovie)
    private val txtYear: TextView = view.findViewById<TextView>(R.id.txtYear_itemMovie)
    private val imgView: ImageView = view.findViewById<ImageView>(R.id.img_itemMovie)

    fun bind(movie: MovieModel) {
        txtName.text = movie.name
        txtGenre.text = movie.genre
        txtStars.text = movie.stars.toString()
        txtYear.text = movie.year.toString()

        Picasso.get()
            .load(movie.img)
            .fit()
            .centerCrop()
            .into(imgView)
    }
}