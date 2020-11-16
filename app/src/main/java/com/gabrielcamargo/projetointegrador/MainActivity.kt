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
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Fonte DK
        val fontDK = ResourcesCompat.getFont(this, R.font.dk_butterfly_ball)
        val textLogo: TextView = findViewById(R.id.txt_logo)
        textLogo.typeface = fontDK

        // ProgressBar
        val maxSplashTime: Long = 3000
        var progressSplash: Long = 0
        val percentResult = maxSplashTime / 101
        val progressSplashBar = findViewById<ProgressBar>(R.id.progressSplashBar)

        for(x in 0..100){
            Handler().postDelayed({
                progressSplashBar.progress = x
            }, progressSplash)
            progressSplash = progressSplash + percentResult
        }

        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        },maxSplashTime)

    }

}