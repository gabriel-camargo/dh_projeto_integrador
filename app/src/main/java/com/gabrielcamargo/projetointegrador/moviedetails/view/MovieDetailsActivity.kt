package com.gabrielcamargo.projetointegrador.moviedetails.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.home.view.HomeFragment
import com.gabrielcamargo.projetointegrador.moviedetails.model.CastModel
import com.gabrielcamargo.projetointegrador.moviedetails.repository.CastRepository
import com.gabrielcamargo.projetointegrador.moviedetails.repository.MovieDetailsRepository
import com.gabrielcamargo.projetointegrador.moviedetails.viewModel.CastViewModel
import com.gabrielcamargo.projetointegrador.moviedetails.viewModel.MovieDetailsViewModel
import com.gabrielcamargo.projetointegrador.utils.movies.model.MovieModel
import com.google.android.material.tabs.TabLayout

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var _castViewModel: CastViewModel
    private lateinit var _viewModel: MovieDetailsViewModel

    private var _movieDetails: MovieModel? = null


    private lateinit var _imgMovie: ImageView
    private lateinit var _movieTitle: TextView
    private var _id: Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        _imgMovie = findViewById(R.id.img_movieDetails)
        _movieTitle = findViewById(R.id.txt_nameMovieDetails)

        _id = intent.getIntExtra(HomeFragment.intentId, 0)

        createMovieDetails()
        createCastView()
        createFragments()

        val back = findViewById<ImageView>(R.id.btn_BackMovieDetails)

        back.setOnClickListener() {
            onBackPressed()
        }


    }

    private fun createMovieDetails() {
        _viewModel = ViewModelProvider(
            this,
            MovieDetailsViewModel.MovieDetailsViewModelFactory(MovieDetailsRepository())
        ).get(MovieDetailsViewModel::class.java)

//        _viewModel.movieDetails.observe(this, Observer {
//            val image = this.findViewById<ImageView>(R.id.img_movieDetails)
//            val name = this.findViewById<TextView>(R.id.txt_nameMovieDetails)
//            val rate = this.findViewById<TextView>(R.id.txt_rateMovieDetails)
//            val genre = this.findViewById<TextView>(R.id.txt_genreMovieDetails)
//            val year = this.findViewById<TextView>(R.id.txt_yearMovieDetails)
//            val time = this.findViewById<TextView>(R.id.txt_timeMovieDetails)
//
//            name.text = it.name
//            rate.text = it.rate.toString()
//            genre.text = it.genre
//            year.text = it.year.toString()
//            time.text = it.time
//
//            image.setImageResource(R.drawable.img_movie)
//        })
//        _viewModel.getMovieDetails()

        _viewModel.getMovieDetails(_id).observe(this, {
            _movieDetails = it

            _movieTitle.text = _movieDetails!!.title

            Glide.with(this)
                .load(_movieDetails!!.getPathPoster())
                .transform()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(_imgMovie)




        })


    }

    private fun createCastView() {
        _castViewModel = ViewModelProvider(
            this,
            CastViewModel.CastViewModelFactory(CastRepository(this))
        ).get(CastViewModel::class.java)

        _castViewModel.cast.observe(this, Observer {
            createCastList(it)
        })

        _castViewModel.getCast()
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

    private fun createFragments() {
        val pager = findViewById<ViewPager>(R.id.viewPagerMovieDetails)

        val fragments =  listOf(
            SummaryFragment()
            , PhotosFragment()
            , ReviewsFragment()
        )

        val titulos = listOf(
            getString(R.string.sinopse), getString(R.string.fotos), getString(R.string.reviews)
        )

        pager.adapter = FragmentsAdapter(fragments, titulos, supportFragmentManager)

    }

}