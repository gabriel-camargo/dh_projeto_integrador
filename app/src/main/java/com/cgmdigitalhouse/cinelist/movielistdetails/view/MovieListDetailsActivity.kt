package com.cgmdigitalhouse.cinelist.movielistdetails.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.drawable.InsetDrawable
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.cgmdigitalhouse.cinelist.R
import com.cgmdigitalhouse.cinelist.db.AppDatabase
import com.cgmdigitalhouse.cinelist.movielistdetails.repository.MovieListDetailsRepository
import com.cgmdigitalhouse.cinelist.movielistdetails.viewmodel.MovieListDetailsViewModel
import com.google.android.material.textfield.TextInputEditText


class MovieListDetailsActivity : AppCompatActivity() {

    private lateinit var _fragment: MovieListDetailsFragment

    private var _id: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list_details)

        if (savedInstanceState == null) {
            val mIntent = intent

            val title: String = mIntent.getStringExtra(getString(R.string.intent_list_name))!!
            val img: Int = mIntent.getIntExtra(getString(R.string.intent_list_img), 0)
            _id = mIntent.getLongExtra("LIST_ID", 0)!!

            _fragment = MovieListDetailsFragment.newInstance(
                title, img, _id!!
            )

            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container, _fragment
                )
                .commitNow()
        }

        val back = findViewById<ImageView>(R.id.btn_BackListDetails)

        back.setOnClickListener() {
            finish()
        }

        val btnMenu = findViewById<ImageView>(R.id.btnMoreVert)
        btnMenu.setOnClickListener {
            showMenu(it, R.menu.list_details_menu)
        }
    }

    private fun showMenu(v: View, @MenuRes menuRes: Int) {
        val popup = PopupMenu(this, v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener{
            onOptionsItemSelected(it)
        }
        popup.setOnDismissListener {
            // Respond to popup being dismissed.
        }
        // Show the popup menu.
        popup.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.list_details_edit -> {
                _fragment.editDialog()
                true
            }
            R.id.list_details_delete -> {
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
                true
            }
            else -> false
        }
    }
}

