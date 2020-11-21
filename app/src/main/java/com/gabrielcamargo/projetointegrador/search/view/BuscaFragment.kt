package com.gabrielcamargo.projetointegrador.search.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.search.model.FilmeModel


class BuscaFragment : Fragment() {


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


            val listaFilmeModel =  mutableListOf<FilmeModel>(FilmeModel("Interstellar","Ficção Científica",8.4,2014,R.drawable.interestelar),
                    FilmeModel("Invocação do Mal","Terror",7.5,2013,R.drawable.invocacao),
                    FilmeModel("Matrix","Ficção Científica",8.7,1999,R.drawable.matrix),
                    FilmeModel("Perdido em Marte","Ficção Científica",8.0,2015,R.drawable.perdidomarte),
                    FilmeModel("Corra!","Terror",7.7,2017,R.drawable.corra),
                    FilmeModel("Vingadores: Ultimato","Ficção Científica",8.7,2019,R.drawable.vingadores))
            val buscaAdapter = BuscaAdapter(listaFilmeModel){

            }

            lista.apply {
                setHasFixedSize(true)

                layoutManager = manager
                adapter = buscaAdapter
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            }
        }
    }




