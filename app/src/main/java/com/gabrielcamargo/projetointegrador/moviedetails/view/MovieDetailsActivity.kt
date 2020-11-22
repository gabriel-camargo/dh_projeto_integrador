package com.gabrielcamargo.projetointegrador.moviedetails.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.model.MovieModel
import com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.repository.WatchlistRepository
import com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.view.WatchListAdapter
import com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.viewmodel.WatchlistViewModel
import com.gabrielcamargo.projetointegrador.moviedetails.model.CastModel
import com.gabrielcamargo.projetointegrador.moviedetails.repository.MovieDetailsRepository
import com.gabrielcamargo.projetointegrador.moviedetails.viewModel.MovieDetailsViewModel
import com.google.android.material.tabs.TabLayout

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var _viewModel: MovieDetailsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        _viewModel = ViewModelProvider(
                this,
                MovieDetailsViewModel.MovieDetailsViewModelFactory(MovieDetailsRepository(this))
        ).get(MovieDetailsViewModel::class.java)

        _viewModel.cast.observe(this, Observer {
            createCastList(it)
        })

        _viewModel.getCast()

        val pager = findViewById<ViewPager>(R.id.viewPagerMovieDetails)
        val tab = findViewById<TabLayout>(R.id.tabMovieDetails)

        val fragments =  listOf(
            SummaryFragment()
            , PhotosFragment()
            , ReviewsFragment()
        )

        val titulos = listOf(
            getString(R.string.sinopse), getString(R.string.fotos), getString(R.string.reviews)
        )

        pager.adapter = MovieDetailsAdapter(fragments, titulos, supportFragmentManager)

    }

    private fun createCastList(cast: List<CastModel>) {
        val viewManagerElenco = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val recyclerViewElenco = findViewById<RecyclerView>(R.id.rcyVwCast)
        val viewAdapterElenco = CastAdapter(cast)

        recyclerViewElenco.apply {
            setHasFixedSize(true)

            layoutManager = viewManagerElenco
            adapter = viewAdapterElenco
        }
    }
}