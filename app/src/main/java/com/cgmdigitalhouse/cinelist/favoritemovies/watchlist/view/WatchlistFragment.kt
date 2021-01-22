package com.cgmdigitalhouse.cinelist.favoritemovies.watchlist.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cgmdigitalhouse.cinelist.R
import com.cgmdigitalhouse.cinelist.db.AppDatabase
import com.cgmdigitalhouse.cinelist.favoritemovies.watchlist.repository.WatchlistRepository
import com.cgmdigitalhouse.cinelist.favoritemovies.watchlist.viewmodel.WatchlistViewModel
import com.cgmdigitalhouse.cinelist.moviedetails.details.repository.MovieDetailsRepository
import com.cgmdigitalhouse.cinelist.moviedetails.details.view.MovieDetailsActivity
import com.cgmdigitalhouse.cinelist.moviedetails.details.viewModel.MovieDetailsViewModel
import com.cgmdigitalhouse.cinelist.utils.SwipeToDeleteCallback
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieCrossRefEntity
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieEntity
import com.cgmdigitalhouse.cinelist.utils.movies.model.MovieModel
import com.cgmdigitalhouse.cinelist.utils.movies.view.VerticalMovieListAdapter
import com.cgmdigitalhouse.cinelist.utils.moviesoffline.model.MovieModelOffline
import com.cgmdigitalhouse.cinelist.utils.moviesoffline.view.MovieOfflineAdapter
import com.google.android.material.snackbar.Snackbar

class WatchlistFragment : Fragment() {
    lateinit var myView: View
    private lateinit var _viewModel: WatchlistViewModel
    private lateinit var _movieDetailsViewModel: MovieDetailsViewModel
    private lateinit var _listMovieCrossRefEntity: MutableList<ListMovieCrossRefEntity>

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
            WatchlistViewModel.WatchlistViewModelFactory(WatchlistRepository(AppDatabase.getDatabase(myView.context).listMovieCrossRefDao()))
        ).get(WatchlistViewModel::class.java)

        _viewModel.getMovies().observe(viewLifecycleOwner, Observer {
            _listMovieCrossRefEntity = it
            createList()
        })

        _viewModel.getMovies()
    }

    private fun createList() {
        val listMovieModel = mutableListOf<MovieModel>()
        _movieDetailsViewModel = ViewModelProvider(
                this,
                MovieDetailsViewModel.MovieDetailsViewModelFactory(MovieDetailsRepository())
        ).get(MovieDetailsViewModel::class.java)
        for (itemListMovie in _listMovieCrossRefEntity){
            _movieDetailsViewModel.getMovieDetails(itemListMovie.movieId.toInt()).observe(viewLifecycleOwner, Observer {
                listMovieModel.add(it)
                addItens(listMovieModel)
            })


        }


    }
    fun addItens(listMovieModel: MutableList<MovieModel>){
        val viewManager = LinearLayoutManager(myView.context)
        val recyclerView = myView.findViewById<RecyclerView>(R.id.recyclerView_watchlistFragment)

        val viewAdapter = VerticalMovieListAdapter(listMovieModel) {
            val intent = Intent(activity, MovieDetailsActivity::class.java)
            startActivity(intent)
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

        val swipeHandler = object : SwipeToDeleteCallback(myView.context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recyclerView.adapter as VerticalMovieListAdapter
                val movieToRemove = adapter.removeAt(viewHolder.adapterPosition)

                _viewModel.removeMovieFromList(movieToRemove.id).observe(viewLifecycleOwner, Observer {
                    Snackbar.make(myView, "${movieToRemove.title} foi removido da watchlist", Snackbar.LENGTH_SHORT).show()
                })
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }


    companion object {
        @JvmStatic
        fun newInstance() = WatchlistFragment()
    }
}