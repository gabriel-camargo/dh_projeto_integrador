package com.gabrielcamargo.projetointegrador

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Fonte DK
        val fontDK = ResourcesCompat.getFont(this,R.font.dk_butterfly_ball)
        val textLogo: TextView = findViewById(R.id.txt_logo)
        textLogo.typeface = fontDK

        // Variaveis
        val edtLoginEmail = findViewById<TextInputEditText>(R.id.edt_loginEmailInput)
        val edtLoginPassword = findViewById<TextInputEditText>(R.id.edt_loginPasswordInput)

    }
}