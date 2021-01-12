package com.cgmdigitalhouse.cinelist.movielistdetails.view

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.cgmdigitalhouse.cinelist.R


class MovieListDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list_details)
        if (savedInstanceState == null) {
            val mIntent = intent

            val title: String = mIntent.getStringExtra(getString(R.string.intent_list_name))!!
            val img: Int = mIntent.getIntExtra(getString(R.string.intent_list_img),0)
            val id: Long = mIntent.getLongExtra("LIST_ID",0)!!
//
//            val ss:String = intent.getStringExtra("samplename")

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieListDetailsFragment.newInstance(
                    title, img, id
                ))
                .commitNow()
        }

        val back = findViewById<ImageView>(R.id.btn_BackListDetails)

        back.setOnClickListener() {
            onBackPressed()
        }

    }
}