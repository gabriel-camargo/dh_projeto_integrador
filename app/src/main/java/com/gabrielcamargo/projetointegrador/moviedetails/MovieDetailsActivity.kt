package com.gabrielcamargo.projetointegrador.moviedetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.gabrielcamargo.projetointegrador.R
import com.google.android.material.tabs.TabLayout

class MovieDetailsActivity : AppCompatActivity() {


    private lateinit var sinopseFragment: SummaryFragment
    private lateinit var fotosFragment: PhotosFragment
    private lateinit var reviewsFragment: ReviewsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val pager = findViewById<ViewPager>(R.id.viewPagerMovieDetails)
        val tab = findViewById<TabLayout>(R.id.tabMovieDetails)

        sinopseFragment = SummaryFragment()
        fotosFragment = PhotosFragment()
        reviewsFragment = ReviewsFragment()

        val fragments =  listOf(
            SummaryFragment.newInstance("O astronauta Mark Watney (Matt Damon) é enviado a uma missão em Marte. Após uma severa tempestade ele é dado como morto, abandonado pelos colegas e acorda sozinho no misterioso planeta com escassos suprimentos, sem saber como reencontrar os companheiros ou retornar à Terra.")
            , fotosFragment
            , reviewsFragment
        )

        val titulos = listOf(
            getString(R.string.sinopse), getString(R.string.fotos), getString(R.string.reviews)
        )

        pager.adapter = MovieDetailsAdapter(fragments, titulos, supportFragmentManager)



        val viewManagerElenco = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val recyclerViewElenco = findViewById<RecyclerView>(R.id.rcyVwCast)

        val mattDamon = Cast("Matt Damon", "Mark Watney", R.drawable.img_cast)
        val elencos = listOf(mattDamon, mattDamon, mattDamon)

        val viewAdapterElenco = CastAdapter(elencos)

        recyclerViewElenco.apply {
            setHasFixedSize(true)

            layoutManager = viewManagerElenco
            adapter = viewAdapterElenco
        }

    }
}