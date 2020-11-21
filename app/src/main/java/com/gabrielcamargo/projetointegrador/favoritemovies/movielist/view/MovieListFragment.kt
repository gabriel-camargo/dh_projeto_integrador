package com.gabrielcamargo.projetointegrador.favoritemovies.movielist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.favoritemovies.movielist.model.MovieListModel
import com.gabrielcamargo.projetointegrador.favoritemovies.movielist.repository.MovieListRepository
import com.gabrielcamargo.projetointegrador.favoritemovies.movielist.viewmodel.MovieListViewModel


class MovieListFragment : Fragment() {
    lateinit var myView: View
    lateinit var viewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView =  inflater.inflate(R.layout.fragment_movie_list, container, false)
        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            MovieListViewModel.MovieListViewModelFactory(MovieListRepository(myView.context))
        ).get(MovieListViewModel::class.java)

        viewModel.movieLists.observe(viewLifecycleOwner, Observer {
            createList(it)
        })

        viewModel.getMovieLists()
    }

    private fun createList(movieLists: List<MovieListModel>) {
        val viewManager = LinearLayoutManager(myView.context)
        val recyclerView = myView.findViewById<RecyclerView>(R.id.recyclerView_movieListFragment)

        val viewAdapter = MovieListAdapter(movieLists) {
            Toast.makeText(myView.context, it.nome, Toast.LENGTH_SHORT).show()
        }

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieListFragment()

    }
}