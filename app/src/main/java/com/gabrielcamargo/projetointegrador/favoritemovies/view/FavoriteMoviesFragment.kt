package com.gabrielcamargo.projetointegrador.favoritemovies.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.favoritemovies.movielist.view.MovieListFragment
import com.google.android.material.tabs.TabLayout

class FavoriteMoviesFragment : Fragment() {

    private val movieListFragment = MovieListFragment()
    private val watchlistFragment = WatchlistFragment()

    private lateinit var myView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView =  inflater.inflate(R.layout.fragment_favorite_movies, container, false)
        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pager: ViewPager = myView.findViewById(R.id.vpMovieList_movieListFragment)
        val tab: TabLayout = myView.findViewById(R.id.tabs_movieListFragment)

        tab.setupWithViewPager(pager)

        val fragments = listOf<Fragment>(movieListFragment, watchlistFragment)
        val titles = listOf<String>(getString(R.string.tab_listas), getString(R.string.tab_watchlist))
        val fragmentManagerTabLayout = (activity as FragmentActivity).supportFragmentManager

        pager.adapter = ViewPagerAdapter(fragments, titles, fragmentManagerTabLayout)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoriteMoviesFragment()
    }
}