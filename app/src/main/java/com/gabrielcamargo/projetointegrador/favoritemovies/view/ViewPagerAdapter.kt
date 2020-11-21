package com.gabrielcamargo.projetointegrador.favoritemovies.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(
    private val fragments: List<Fragment>,
    private val titles: List<String>,
    manager: FragmentManager
): FragmentPagerAdapter(
    manager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    //quantidade de tabs
    override fun getCount() = this.fragments.size

    //retorna o fragment correspondente
    override fun getItem(position: Int) = this.fragments[position]

    //retorna o nome da tab
    override fun getPageTitle(position: Int) = this.titles[position]
}
