package com.gabrielcamargo.projetointegrador.movielistdetails.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gabrielcamargo.projetointegrador.R


class MovieListDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list_details)
        if (savedInstanceState == null) {
            val mIntent = intent

            val title: String = mIntent.getStringExtra(getString(R.string.intent_list_name))!!
            val img: Int = mIntent.getIntExtra(getString(R.string.intent_list_img),0)
//
//            val ss:String = intent.getStringExtra("samplename")



            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieListDetailsFragment.newInstance(
                    title, img
                ))
                .commitNow()
        }
    }
}
