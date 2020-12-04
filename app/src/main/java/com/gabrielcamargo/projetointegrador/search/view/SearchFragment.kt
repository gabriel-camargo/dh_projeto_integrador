package com.gabrielcamargo.projetointegrador.search.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.moviedetails.view.MovieDetailsActivity
import com.gabrielcamargo.projetointegrador.utils.movies.model.MovieModel
import com.gabrielcamargo.projetointegrador.utils.movies.view.MovieAdapter


class SearchFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val lista = view.findViewById<RecyclerView>(R.id.recyclerViewSearch)
            val manager = LinearLayoutManager(view.context)


            val listaMovieModel =  mutableListOf<MovieModel>(MovieModel("Interstellar",8.4,"Ficção Científica",2014,R.drawable.interestelar),
                    MovieModel("Invocação do Mal",7.5,"Terror",2013,R.drawable.invocacao),
                    MovieModel("Matrix",8.7,"Ficção Científica",1999,R.drawable.matrix),
                    MovieModel("Perdido em Marte",8.0,"Ficção Científica",2015,R.drawable.perdidomarte),
                    MovieModel("Corra!",7.7,"Terror",2017,R.drawable.corra),
                    MovieModel("Vingadores: Ultimato",8.7,"Ficção Científica",2019,R.drawable.vingadores))
            val buscaAdapter = MovieAdapter(listaMovieModel){
                val intent = Intent(view.context, MovieDetailsActivity::class.java)
                startActivity(intent)
            }

            lista.apply {
                setHasFixedSize(true)

                layoutManager = manager
                adapter = buscaAdapter
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            }
        }
    }




