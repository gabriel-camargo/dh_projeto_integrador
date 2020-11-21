package com.gabrielcamargo.projetointegrador.favoritemovies.movielist.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.favoritemovies.movielist.model.MovieListModel
import org.w3c.dom.Text

class MovieListViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val txtName: TextView = view.findViewById(R.id.txtName_itemMovieList)
    private val qtdMovies: TextView = view.findViewById(R.id.txtCount_itemMovieList)

    fun bind(movieList: MovieListModel) {
        txtName.text = movieList.nome
        qtdMovies.text = movieList.qtd.toString()
    }
}
