package com.gabrielcamargo.projetointegrador.search.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.moviedetails.view.MovieDetailsActivity
import com.gabrielcamargo.projetointegrador.search.repository.SearchRepository
import com.gabrielcamargo.projetointegrador.search.viewmodel.SearchViewModel
import com.gabrielcamargo.projetointegrador.utils.moviesoffline.model.MovieModelOffline
import com.gabrielcamargo.projetointegrador.utils.moviesoffline.view.MovieOfflineAdapter

class SearchFragment : Fragment() {

    private lateinit var _viewModel: SearchViewModel
    private lateinit var _myView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _myView = inflater.inflate(R.layout.fragment_search, container, false)
        return _myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        _viewModel = ViewModelProvider(
            this,
            SearchViewModel.SearchViewModelFactory(
                SearchRepository(_myView.context)
            )
        ).get(SearchViewModel::class.java)

        _viewModel.movies.observe(viewLifecycleOwner, Observer {
            createList(it)
        })

        _viewModel.getMovies()
    }

    private fun createList(movies: List<MovieModelOffline>) {
        val recyclerView = _myView.findViewById<RecyclerView>(R.id.recyclerViewSearch)
        val manager = LinearLayoutManager(_myView.context)

        val viewAdapter = MovieOfflineAdapter(movies) {
            val intent = Intent(_myView.context, MovieDetailsActivity::class.java)
            startActivity(intent)
        }

        recyclerView.apply {
            setHasFixedSize(true)

            layoutManager = manager
            adapter = viewAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }
}




