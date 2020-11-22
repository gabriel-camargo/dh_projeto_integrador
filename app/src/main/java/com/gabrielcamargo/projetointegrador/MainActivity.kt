package com.gabrielcamargo.projetointegrador


import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.gabrielcamargo.projetointegrador.favoritemovies.view.FavoriteMoviesFragment
import com.gabrielcamargo.projetointegrador.home.view.HomeFragment
import com.gabrielcamargo.projetointegrador.search.view.BuscaFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener,
    BottomNavigationView.OnNavigationItemReselectedListener {

    private lateinit var  navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentContainer = this.findViewById<FragmentContainerView>(R.id.fragment_mainActivity)

        navController = Navigation.findNavController(fragmentContainer)

        initBottomNavigation()

    }

    private fun initBottomNavigation() {
        val botNav = this.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        botNav?.setOnNavigationItemSelectedListener( this )
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment: Int = when (item.itemId) {
            R.id.page_1 -> {
                R.id.homeFragment
            }
            R.id.page_2 -> {
                R.id.buscaFragment
            }
            R.id.page_3 -> {
                R.id.favoriteMoviesFragment
            }
            else -> {
                R.id.homeFragment
            }
        }
        changeFragment( fragment )

        return true
    }

    private fun changeFragment( fragment: Int ){
        navController.navigate(fragment)
    }

    override fun onNavigationItemReselected(item: MenuItem) {
        /* TODO */
    }





}