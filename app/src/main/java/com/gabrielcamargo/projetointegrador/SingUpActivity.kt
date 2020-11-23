package com.gabrielcamargo.projetointegrador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.textfield.TextInputEditText

class SingUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

        // Fonte DK
        val fontDK = ResourcesCompat.getFont(this,R.font.dk_butterfly_ball)
        val textLogo: TextView = findViewById(R.id.txt_logo)
        textLogo.typeface = fontDK

        // Variaveis
        var edtLoginEmail = findViewById<TextInputEditText>(R.id.edt_loginEmailInput)
    }
}