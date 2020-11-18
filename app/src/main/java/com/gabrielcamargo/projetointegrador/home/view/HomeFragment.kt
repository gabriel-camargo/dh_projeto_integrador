package com.gabrielcamargo.projetointegrador.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.home.model.Movie

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
        val viewManager = GridLayoutManager(view.context,SPAN_COUNT)

        val movies = arrayListOf<Movie>(Movie("Viuva Negra",R.drawable.viuva),
            Movie("Vingadores: Ultimato",R.drawable.vingadores),
            Movie("Sonic",R.drawable.sonic),Movie("John Wick",R.drawable.jhon)
            )

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerCard)

        val viewAdapter = HomeAdapter(movies){}

        recyclerView.apply {
            setHasFixedSize(true)

            layoutManager = viewManager
            adapter = viewAdapter
        }
    }


}