package com.gabrielcamargo.projetointegrador.movielistdetails.view

import android.media.Image
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.favoritemovies.movielist.repository.MovieListRepository
import com.gabrielcamargo.projetointegrador.favoritemovies.movielist.viewmodel.MovieListViewModel
import com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.model.MovieModel
import com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.view.WatchListAdapter
import com.gabrielcamargo.projetointegrador.movielistdetails.repository.MovieListDetailsRepository
import com.gabrielcamargo.projetointegrador.movielistdetails.viewmodel.MovieListDetailsViewModel
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

private const val ARG_PARAM_TITLE = "title"
private const val ARG_PARAM_IMG = "img"

class MovieListDetailsFragment : Fragment() {

    private var title: String? = null
    private var img: Int? = null

    private lateinit var _viewModel: MovieListDetailsViewModel
    private lateinit var _myView: View

    companion object {
        fun newInstance(title: String, img: Int) =
            MovieListDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_TITLE, title)
                    putInt(ARG_PARAM_IMG, img)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_PARAM_TITLE)
            img = it.getInt(ARG_PARAM_IMG)
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
            MovieListDetailsViewModel.MovieListDetailsViewModelFactory(MovieListDetailsRepository(_myView.context))
        ).get(MovieListDetailsViewModel::class.java)

        _viewModel.movies.observe(viewLifecycleOwner, Observer {
            createList(it)
        })

        _viewModel.getMovies()
    }

    private fun setDataMovieDetails() {
        val txtName: TextView = _myView.findViewById(R.id.txtTitle_movieListDetailsFragment)
        val txtDesc: TextView = _myView.findViewById(R.id.txtDesc_movieListDetailsFragment)
        val imgView: ImageView = _myView.findViewById(R.id.img_movieListDetailsFragment)

        txtName.text = this.title
        txtDesc.text = getString(R.string.descricao_lista_exemplo)

        img?.let {
            Picasso.get()
                .load(it)
                .fit()
                .centerCrop()
                .into(imgView)
        }


    }

    private fun createList(movies: List<MovieModel>) {
        val viewManager = LinearLayoutManager(_myView.context)
        val recyclerView = _myView.findViewById<RecyclerView>(R.id.recyclerView_movieListDetailsFragment)

        val viewAdapter = WatchListAdapter(movies) {
            Toast.makeText(_myView.context, it.name, Toast.LENGTH_SHORT).show()
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