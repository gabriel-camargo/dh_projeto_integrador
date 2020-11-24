package com.gabrielcamargo.projetointegrador.favoritemovies.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gabrielcamargo.projetointegrador.favoritemovies.movielist.view.MovieListFragment
import com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.view.WatchlistFragment

class ViewPagerAdapter(
    fragment: Fragment
): FragmentStateAdapter(
    fragment
) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = if(position==0) {
        MovieListFragment()
    } else {
        WatchlistFragment()
    }


}
