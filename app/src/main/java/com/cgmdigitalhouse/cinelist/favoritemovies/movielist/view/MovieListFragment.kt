package com.cgmdigitalhouse.cinelist.favoritemovies.movielist.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cgmdigitalhouse.cinelist.R
import com.cgmdigitalhouse.cinelist.db.AppDatabase
import com.cgmdigitalhouse.cinelist.favoritemovies.movielist.model.MovieListModel
import com.cgmdigitalhouse.cinelist.favoritemovies.movielist.repository.MovieListRepository
import com.cgmdigitalhouse.cinelist.favoritemovies.movielist.viewmodel.MovieListViewModel
import com.cgmdigitalhouse.cinelist.movielistdetails.view.MovieListDetailsActivity
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieEntity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText


class MovieListFragment : Fragment() {
    lateinit var myView: View
    lateinit var viewModel: MovieListViewModel
    lateinit var mAlertDialog: AlertDialog
    lateinit var movieLists: MutableList<ListMovieEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView =  inflater.inflate(R.layout.fragment_movie_list, container, false)
        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            MovieListViewModel.MovieListViewModelFactory(MovieListRepository(AppDatabase.getDatabase(myView.context).listMovieDao()))
        ).get(MovieListViewModel::class.java)

         viewModel.getMovieLists().observe(viewLifecycleOwner, {
                movieLists = it
                createList()
         })
        addItemList()

    }

    private fun createList() {
        val viewManager = LinearLayoutManager(myView.context)
        val recyclerView = myView.findViewById<RecyclerView>(R.id.recyclerView_movieListFragment)


        val viewAdapter = MovieListAdapter(movieLists) {

            val intent = Intent(activity, MovieListDetailsActivity::class.java)
            intent.putExtra(getString(R.string.intent_list_name), it.name)
            intent.putExtra("LIST_ID", it.listMovieId)

            //intent.putExtra(getString(R.string.intent_list_img), it.img)

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
    private fun addItemList(){
        val btnCreaterList = myView.findViewById<FloatingActionButton>(R.id.fbtnCreatList_movieListFragment)
        btnCreaterList.setOnClickListener {
            val mDialogView =
                    LayoutInflater.from(myView.context).inflate(R.layout.list_dialog, null)

            val mBuilder = AlertDialog.Builder(myView.context).setView(mDialogView)
                    .setTitle("Criar Lista de Filmes")
            mAlertDialog = mBuilder.show()

            val btnCancelar = mDialogView.findViewById<Button>(R.id.btn_cancelar)
            val btnCriar = mDialogView.findViewById<Button>(R.id.btn_criar)
            val edtName = mDialogView.findViewById<TextInputEditText>(R.id.edt_nameListInput)
            val edtDescription = mDialogView.findViewById<TextInputEditText>(R.id.edt_descriptionListInput)

            btnCancelar.setOnClickListener {
                mAlertDialog.dismiss()
            }

            btnCriar.setOnClickListener {
                mAlertDialog.dismiss()
                viewModel.inserirListMovie(edtName.text.toString(),edtDescription.text.toString()).observe(viewLifecycleOwner, {
                    movieLists.add(it)
                    createList()
                })

            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieListFragment()

    }
}