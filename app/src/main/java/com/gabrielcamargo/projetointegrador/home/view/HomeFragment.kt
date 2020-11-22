package com.gabrielcamargo.projetointegrador.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.home.model.FilmeModel

const val  SPAN_COUNT = 2

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewManagerPopular = LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)
        val viewManagerCinema = LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)

        val filmesPopular = arrayListOf<FilmeModel>(FilmeModel("Viuva Negra",R.drawable.viuva),
                FilmeModel("Sonic",R.drawable.sonic),
                FilmeModel("John Wick",R.drawable.jhon),
                FilmeModel("matrix",R.drawable.matrix),
                FilmeModel("Perdido em Marte",R.drawable.perdidomarte),
                FilmeModel("corra!",R.drawable.corra)
            )
        val filmesCinema = arrayListOf<FilmeModel>(
                FilmeModel("matrix",R.drawable.matrix),
                FilmeModel("Perdido em Marte",R.drawable.perdidomarte),

                FilmeModel("corra!",R.drawable.corra),
                FilmeModel("Viuva Negra",R.drawable.viuva),
                FilmeModel("Sonic",R.drawable.sonic),
                FilmeModel("John Wick",R.drawable.jhon)
            )

        val recyclerViewPopular = view.findViewById<RecyclerView>(R.id.recyclerCardPopular)
        val recyclerViewCinema = view.findViewById<RecyclerView>(R.id.recyclerCardCinema)

        val viewAdapterPopular = HomeAdapter(filmesPopular){}
        val viewAdapterCinema = HomeAdapter(filmesCinema){}

        recyclerViewPopular.apply {
            setHasFixedSize(true)

            layoutManager = viewManagerPopular
            adapter = viewAdapterPopular

        }
        recyclerViewCinema.apply {
            setHasFixedSize(true)

            layoutManager = viewManagerCinema
            adapter = viewAdapterCinema
        }
    }


}