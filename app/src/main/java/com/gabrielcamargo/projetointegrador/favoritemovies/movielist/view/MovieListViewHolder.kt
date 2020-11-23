package com.gabrielcamargo.projetointegrador.favoritemovies.movielist.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.favoritemovies.movielist.model.MovieListModel
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class MovieListViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val txtName: TextView = view.findViewById(R.id.txtName_itemMovieList)
    private val movies: TextView = view.findViewById(R.id.txtCount_itemMovieList)
    private val img: ImageView = view.findViewById(R.id.img_itemMovieList)

    fun bind(movieList: MovieListModel) {
        txtName.text = movieList.nome
        movies.text = movieList.qtd.toString()

        Picasso.get()
            .load(movieList.img)
            .fit()
            .centerCrop()
            .into(img)
    }
}
