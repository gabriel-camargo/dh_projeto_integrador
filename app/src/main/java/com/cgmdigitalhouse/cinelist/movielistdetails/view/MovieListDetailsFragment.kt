package com.cgmdigitalhouse.cinelist.movielistdetails.view

import android.app.AlertDialog
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
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
import com.cgmdigitalhouse.cinelist.utils.SwipeToDeleteCallback
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieCrossRefEntity
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieEntity
import com.cgmdigitalhouse.cinelist.utils.movies.model.MovieModel
import com.cgmdigitalhouse.cinelist.utils.movies.view.VerticalMovieListAdapter
import com.cgmdigitalhouse.cinelist.utils.moviesoffline.model.MovieModelOffline
import com.cgmdigitalhouse.cinelist.utils.moviesoffline.view.MovieOfflineAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso

private const val ARG_PARAM_TITLE = "title"
private const val ARG_PARAM_IMG = "img"
private const val ARG_PARAM_ID= "id"

class MovieListDetailsFragment : Fragment() {

    private var _title: String? = null
    private var _description: String? = null
    private var _img: String? = null
    private var _id: Long? = null
    private  var _movies = mutableListOf<MovieModel>()

    private lateinit var _viewModel: MovieListDetailsViewModel
    private lateinit var _movieDetailsViewModel: MovieDetailsViewModel
    private lateinit var _myView: View

    private lateinit var _mAlertDialog: AlertDialog

    companion object {
        fun newInstance(title: String, img: String, id: Long) =
            MovieListDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_TITLE, title)
                    putString(ARG_PARAM_IMG, img)
                    putLong(ARG_PARAM_ID, id)
                }
            }

        private const val CARD_CORNER_RADIUS = 20
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            _title = it.getString(ARG_PARAM_TITLE)
            _img = it.getString(ARG_PARAM_IMG)
            _id = it.getLong(ARG_PARAM_ID)
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

        _viewModel = ViewModelProvider(
            this,
            MovieListDetailsViewModel.MovieListDetailsViewModelFactory(
                MovieListDetailsRepository(
                    AppDatabase.getDatabase(_myView.context).listMovieDao(),
                    AppDatabase.getDatabase(_myView.context).listMovieCrossRefDao()
                )
            )
        ).get(MovieListDetailsViewModel::class.java)

        _viewModel.getListDetais(_id!!).observe(viewLifecycleOwner, Observer { list ->
            setDataMovieDetails(list[0])
        })

        _viewModel.getListMoviesCrossRefEntity(_id!!).observe(viewLifecycleOwner, Observer {
            createList(it)
        })
    }

    private fun setDataMovieDetails(movieList: ListMovieEntity) {
        val firebase = FirebaseStorage.getInstance()
        val storage = firebase.getReference("uploads")

        val txtName: TextView = _myView.findViewById(R.id.txtTitle_movieListDetailsFragment)
        val txtDesc: TextView = _myView.findViewById(R.id.txtDesc_movieListDetailsFragment)
        val imgView: ImageView = _myView.findViewById(R.id.img_movieListDetailsFragment)

        _title = movieList.name
        _description = movieList.description


        txtName.text = movieList.name
        txtDesc.text = movieList.description

        storage.child(movieList.imageURL.substringAfter("uploads/")).downloadUrl.addOnSuccessListener {
            Picasso.get().load(it).into(imgView)
        }

//        _img?.let {
//            Glide.with(_myView.context)
//                .load(it)
//                .transform(CenterCrop(), RoundedCorners(CARD_CORNER_RADIUS))
//                .transition(DrawableTransitionOptions.withCrossFade())
//                .into(imgView)
//        }
    }

    private fun createList(listMovieCrossRefEntity: MutableList<ListMovieCrossRefEntity>) {
        _movieDetailsViewModel = ViewModelProvider(
                this,
                MovieDetailsViewModel.MovieDetailsViewModelFactory(MovieDetailsRepository())
        ).get(MovieDetailsViewModel::class.java)

        for (listMovie in listMovieCrossRefEntity){
            _movieDetailsViewModel.getMovieDetails(listMovie.movieId.toInt()).observe(viewLifecycleOwner, Observer {
                _movies.add(it)
                addItens(_movies)
            })
        }
    }

    fun addItens(movies :MutableList<MovieModel>){
        val viewManager = LinearLayoutManager(_myView.context)
        val recyclerView =
                _myView.findViewById<RecyclerView>(R.id.recyclerView_movieListDetailsFragment)
        val viewAdapter = VerticalMovieListAdapter(movies) {

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

        val swipeHandler = object : SwipeToDeleteCallback(_myView.context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recyclerView.adapter as VerticalMovieListAdapter
                val movieToRemove = adapter.removeAt(viewHolder.adapterPosition)

                _viewModel.removeMovieFromList(_id!!, movieToRemove.id).observe(viewLifecycleOwner, Observer {
                    Snackbar.make(_myView, "${movieToRemove.title} removido da lista", Snackbar.LENGTH_SHORT).show()
                })
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    fun editDialog() {
        val mDialogView =
            LayoutInflater.from(_myView.context).inflate(R.layout.list_dialog, null)

        val mBuilder = AlertDialog.Builder(_myView.context).setView(mDialogView)
            .setTitle("Editar Lista de Filmes")
        _mAlertDialog = mBuilder.show()

        val btnCancelar = mDialogView.findViewById<Button>(R.id.btn_cancelar)
        val btnCriar = mDialogView.findViewById<Button>(R.id.btn_criar)
        btnCriar.text=getString(R.string.editar)

        val edtName = mDialogView.findViewById<TextInputEditText>(R.id.edt_nameListInput)
        val edtDescription = mDialogView.findViewById<TextInputEditText>(R.id.edt_descriptionListInput)

        edtName.setText(this._title!!)
        edtDescription.setText(this._description!!)

        btnCancelar.setOnClickListener {
            _mAlertDialog.dismiss()
        }

        btnCriar.setOnClickListener {

            val listName = edtName.text.toString().trim()
            val listDescription = edtDescription.text.toString().trim()

            if(listName.isBlank()) {
                Toast.makeText(_myView.context, "Você deve preencher um nome válido para a lista", Toast.LENGTH_LONG).show()
            } else {
                this.editMovieList(_id!!, listName, listDescription)

                val txtName: TextView = _myView.findViewById(R.id.txtTitle_movieListDetailsFragment)
                val txtDesc: TextView = _myView.findViewById(R.id.txtDesc_movieListDetailsFragment)

                txtName.text = listName
                txtDesc.text = listDescription

                _mAlertDialog.dismiss()
            }
        }
    }

    private fun editMovieList(id: Long, name: String, description: String) {
        _viewModel.editList(id, name, description,"").observe(viewLifecycleOwner, Observer {
            Toast.makeText(_myView.context, "Edição salva com sucesso", Toast.LENGTH_SHORT).show()
        })
    }

    fun deleteMovieList() {
        _viewModel.deleteList(_id!!).observe(viewLifecycleOwner, Observer {
            Toast.makeText(_myView.context, "Lista excluída com sucesso", Toast.LENGTH_SHORT).show()
        })

        activity?.finish()
    }
}