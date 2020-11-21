package com.gabrielcamargo.projetointegrador.movielistdetails.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import org.w3c.dom.Text

class MovieListDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = MovieListDetailsFragment()
    }

    private lateinit var _viewModel: MovieListDetailsViewModel
    private lateinit var _myView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _myView = inflater.inflate(R.layout.movie_list_details_fragment, container, false)
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

        txtName.text = getString(R.string.nome_lista_exemplo)
        txtDesc.text = getString(R.string.descricao_lista_exemplo)
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