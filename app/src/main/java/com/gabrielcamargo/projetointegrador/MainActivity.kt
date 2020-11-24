package com.gabrielcamargo.projetointegrador

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gabrielcamargo.projetointegrador.account.view.AccountFragment
import com.gabrielcamargo.projetointegrador.favoritemovies.view.FavoriteMoviesFragment
import com.gabrielcamargo.projetointegrador.home.view.HomeFragment
import com.gabrielcamargo.projetointegrador.search.view.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigation: BottomNavigationView
    private val favoriteMoviesFragment: FavoriteMoviesFragment = FavoriteMoviesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transactionFragment = supportFragmentManager.beginTransaction()
        transactionFragment.replace(R.id.frameLayout_mainActivity, HomeFragment())
        transactionFragment.commit()

        bottomNavigation = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.page_home -> {
                    setupFragment(HomeFragment())
                    true
                }
                R.id.page_search -> {
                    setupFragment(SearchFragment())
                    // Respond to navigation item 2 click
                    true
                }
                R.id.page_lists -> {
                    setupFragment(favoriteMoviesFragment)

                    // Respond to navigation item 2 click
                    true
                }
                R.id.page_user -> {
                    setupFragment(AccountFragment())

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
