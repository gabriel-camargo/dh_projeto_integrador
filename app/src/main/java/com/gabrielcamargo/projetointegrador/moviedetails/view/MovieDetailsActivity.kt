package com.gabrielcamargo.projetointegrador.moviedetails.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.home.view.HomeFragment
import com.gabrielcamargo.projetointegrador.moviedetails.model.CastModel
import com.gabrielcamargo.projetointegrador.moviedetails.repository.CastRepository
import com.gabrielcamargo.projetointegrador.moviedetails.repository.MovieDetailsRepository
import com.gabrielcamargo.projetointegrador.moviedetails.viewModel.CastViewModel
import com.gabrielcamargo.projetointegrador.moviedetails.viewModel.MovieDetailsViewModel
import com.gabrielcamargo.projetointegrador.utils.movies.model.MovieModel

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var _castViewModel: CastViewModel
    private lateinit var _viewModel: MovieDetailsViewModel

    private var _movieDetails: MovieModel? = null


    private lateinit var _imgMovie: ImageView
    private lateinit var _movieTitle: TextView
    private lateinit var _movieRate: TextView
    private lateinit var _movieYear: TextView
    private lateinit var _movieGenre: TextView
    private lateinit var _movieTime: TextView
    private var _id: Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        _imgMovie = findViewById(R.id.img_movieDetails)
        _movieTitle = findViewById(R.id.txt_nameMovieDetails)
        _movieRate = findViewById(R.id.txt_rateMovieDetails)
        _movieYear = findViewById(R.id.txt_yearMovieDetails)
        _movieGenre = findViewById(R.id.txt_genreMovieDetails)
        _movieTime = findViewById(R.id.txt_timeMovieDetails)

        _id = intent.getIntExtra(HomeFragment.intentId, 0)

        createMovieDetails()
        createCastView()

        val back = findViewById<ImageView>(R.id.btn_BackMovieDetails)

        back.setOnClickListener() {
            onBackPressed()
        }


    }

    @SuppressLint("SetTextI18n")
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

            val runtime = _movieDetails!!.runtime
            val hours: Int = runtime / 60
            val minutes: Int = runtime % 60

            _movieTitle.text = _movieDetails!!.title
            _movieRate.text = _movieDetails!!.voteAverage.toString()
            _movieGenre.text = _movieDetails!!.genres[0].name
            _movieTime.text = "%dh %02dmin".format(hours, minutes)


            _movieDetails!!.releaseDate?.let {

                _movieYear.text = it.split("-")[0].toString()
            }

            Glide.with(this)
                .load(_movieDetails!!.getPathPoster())
                .transform()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(_imgMovie)

            createFragments()

        })


    }

    private fun createCastView() {
        _castViewModel = ViewModelProvider(
            this,
            CastViewModel.CastViewModelFactory(CastRepository())
        ).get(CastViewModel::class.java)

        _castViewModel.getCast(_id).observe(this, {
            if (it != null) {
                createCastList(it)
            }
        })
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
            SummaryFragment.newInstance(_movieDetails!!.overview), PhotosFragment.newInstance(_movieDetails!!.id), ReviewsFragment.newInstance(_movieDetails!!.id)
        )

        val titulos = listOf(
            getString(R.string.sinopse), getString(R.string.fotos), getString(R.string.reviews)
        )

        pager.adapter = FragmentsAdapter(fragments, titulos, supportFragmentManager)

    }

}