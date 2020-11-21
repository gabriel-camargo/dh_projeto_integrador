package com.gabrielcamargo.projetointegrador.moviedetails.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.viewmodel.WatchlistViewModel
import com.gabrielcamargo.projetointegrador.moviedetails.model.CastModel
import com.gabrielcamargo.projetointegrador.moviedetails.viewModel.MovieDetailsViewModel
import com.google.android.material.tabs.TabLayout

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var _viewModel: MovieDetailsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val pager = findViewById<ViewPager>(R.id.viewPagerMovieDetails)
        val tab = findViewById<TabLayout>(R.id.tabMovieDetails)

        val fragments =  listOf(
            SummaryFragment.newInstance("O astronauta Mark Watney (Matt Damon) é enviado a uma missão em Marte. Após uma severa tempestade ele é dado como morto, abandonado pelos colegas e acorda sozinho no misterioso planeta com escassos suprimentos, sem saber como reencontrar os companheiros ou retornar à Terra.")
            , PhotosFragment()
            , ReviewsFragment()
        )

        val titulos = listOf(
            getString(R.string.sinopse), getString(R.string.fotos), getString(R.string.reviews)
        )

        pager.adapter = MovieDetailsAdapter(fragments, titulos, supportFragmentManager)



        val viewManagerElenco = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val recyclerViewElenco = findViewById<RecyclerView>(R.id.rcyVwCast)

        val mattDamon = CastModel("Matt Damon", "Mark Watney", R.drawable.img_cast)
        val cast = listOf(mattDamon, mattDamon, mattDamon, mattDamon, mattDamon)

        val viewAdapterElenco = CastAdapter(cast)

        recyclerViewElenco.apply {
            setHasFixedSize(true)

            layoutManager = viewManagerElenco
            adapter = viewAdapterElenco
        }

    }
}