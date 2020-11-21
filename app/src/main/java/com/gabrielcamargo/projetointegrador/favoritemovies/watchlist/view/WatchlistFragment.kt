package com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.model.MovieModel
import com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.repository.WatchlistRepository
import com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.viewmodel.WatchlistViewModel

class WatchlistFragment : Fragment() {
    lateinit var myView: View
    private lateinit var _viewModel: WatchlistViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_watchlist, container, false)
        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewModel = ViewModelProvider(
                this,
                WatchlistViewModel.WatchlistViewModelFactory(WatchlistRepository(myView.context))
        ).get(WatchlistViewModel::class.java)

        _viewModel.movies.observe(viewLifecycleOwner, Observer {
            createList(it)
        })

        _viewModel.getMovies()
    }

    private fun createList(movies: List<MovieModel>) {
        val viewManager = LinearLayoutManager(myView.context)
        val recyclerView = myView.findViewById<RecyclerView>(R.id.recyclerView_watchlistFragment)

        val viewAdapter = WatchListAdapter(movies) {
            Toast.makeText(myView.context, it.name, Toast.LENGTH_SHORT).show()
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
        fun newInstance() = WatchlistFragment()
    }
}