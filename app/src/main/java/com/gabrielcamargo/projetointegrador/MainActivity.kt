package com.gabrielcamargo.projetointegrador

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.gabrielcamargo.projetointegrador.favoritemovies.view.FavoriteMoviesFragment
import com.gabrielcamargo.projetointegrador.home.view.HomeFragment
import com.gabrielcamargo.projetointegrador.search.view.BuscaFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigation: BottomNavigationView
    private val favoriteMoviesFragment: FavoriteMoviesFragment = FavoriteMoviesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.page_home -> {
                    setupFragment(HomeFragment())
                    true
                }
                R.id.page_search -> {
                    setupFragment(BuscaFragment())
                    // Respond to navigation item 2 click
                    true
                }
                R.id.page_lists -> {
                    setupFragment(favoriteMoviesFragment)

                    // Respond to navigation item 2 click
                    true
                }
                R.id.page_user -> {
                    setupFragment(HomeFragment())

                    // Respond to navigation item 2 click
                    true
                }
                else -> false
            }
        }

    }

    private fun setupFragment(fragment: Fragment) {
        val transactionFragment = supportFragmentManager.beginTransaction()
        transactionFragment.replace(R.id.frameLayout_mainActivity, fragment)
        transactionFragment.commit()
    }
}
