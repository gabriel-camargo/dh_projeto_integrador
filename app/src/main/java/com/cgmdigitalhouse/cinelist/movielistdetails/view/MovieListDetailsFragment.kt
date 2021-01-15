package com.cgmdigitalhouse.cinelist.movielistdetails.view

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.cgmdigitalhouse.cinelist.R
import com.cgmdigitalhouse.cinelist.db.AppDatabase
import com.cgmdigitalhouse.cinelist.home.view.HomeFragment
import com.cgmdigitalhouse.cinelist.moviedetails.details.repository.MovieDetailsRepository
import com.cgmdigitalhouse.cinelist.moviedetails.details.view.MovieDetailsActivity
import com.cgmdigitalhouse.cinelist.moviedetails.details.viewModel.MovieDetailsViewModel
import com.cgmdigitalhouse.cinelist.movielistdetails.repository.MovieListDetailsRepository
import com.cgmdigitalhouse.cinelist.movielistdetails.viewmodel.MovieListDetailsViewModel
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieCrossRefEntity
import com.cgmdigitalhouse.cinelist.utils.movies.model.MovieModel
import com.cgmdigitalhouse.cinelist.utils.moviesoffline.model.MovieModelOffline
import com.cgmdigitalhouse.cinelist.utils.moviesoffline.view.MovieOfflineAdapter

private const val ARG_PARAM_TITLE = "title"
private const val ARG_PARAM_IMG = "img"
private const val ARG_PARAM_ID= "id"

class MovieListDetailsFragment : Fragment() {

    private var title: String? = null
    private var img: Int? = null
    private var id: Long? = null
    private  var movies = mutableListOf<MovieModel>()

    private lateinit var _viewModel: MovieListDetailsViewModel
    private lateinit var _movieDetailsViewModel: MovieDetailsViewModel
    private lateinit var _myView: View

    companion object {
        fun newInstance(title: String, img: Int, id: Long) =
            MovieListDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_TITLE, title)
                    putInt(ARG_PARAM_IMG, img)
                    putLong(ARG_PARAM_ID, id)
                }
            }

        private const val CARD_CORNER_RADIUS = 20

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_PARAM_TITLE)
            img = it.getInt(ARG_PARAM_IMG)
            id = it.getLong(ARG_PARAM_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _myView = inflater.inflate(R.layout.fragment_movie_list_details, container, false)
        return _myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDataMovieDetails()

        _viewModel = ViewModelProvider(
            this,
            MovieListDetailsViewModel.MovieListDetailsViewModelFactory(
                MovieListDetailsRepository(
                        AppDatabase.getDatabase(_myView.context).listMovieCrossRefDao()
                )
            )
        ).get(MovieListDetailsViewModel::class.java)

        _viewModel.getListMoviesCrossRefEntity(id!!).observe(viewLifecycleOwner, Observer {
            createList(it)
        })


    }

    private fun setDataMovieDetails() {
        val txtName: TextView = _myView.findViewById(R.id.txtTitle_movieListDetailsFragment)
        val txtDesc: TextView = _myView.findViewById(R.id.txtDesc_movieListDetailsFragment)
        val imgView: ImageView = _myView.findViewById(R.id.img_movieListDetailsFragment)

        txtName.text = this.title
        txtDesc.text = getString(R.string.descricao_lista_exemplo)

        img?.let {
            Glide.with(_myView.context)
                .load(it)
                .transform(CenterCrop(), RoundedCorners(CARD_CORNER_RADIUS))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imgView)
        }
    }

    private fun createList(listMovieCrossRefEntity: MutableList<ListMovieCrossRefEntity>) {
        _movieDetailsViewModel = ViewModelProvider(
                this,
                MovieDetailsViewModel.MovieDetailsViewModelFactory(MovieDetailsRepository())
        ).get(MovieDetailsViewModel::class.java)

        for (listMovie in listMovieCrossRefEntity){
            _movieDetailsViewModel.getMovieDetails(listMovie.movieId.toInt()).observe(viewLifecycleOwner, Observer {
                movies.add(it)
                addItens(movies)
            })
        }
    }

    fun addItens(movies :MutableList<MovieModel>){
        val viewManager = LinearLayoutManager(_myView.context)
        val recyclerView =
                _myView.findViewById<RecyclerView>(R.id.recyclerView_movieListDetailsFragment)
        val viewAdapter = MovieOfflineAdapter(movies) {

            val intent = Intent(activity, MovieDetailsActivity::class.java)
            intent.putExtra(HomeFragment.intentId, it.id)
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
    }


}