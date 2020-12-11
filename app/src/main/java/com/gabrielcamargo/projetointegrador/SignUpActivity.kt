package com.gabrielcamargo.projetointegrador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.textfield.TextInputEditText

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Fonte DK
        val fontDK = ResourcesCompat.getFont(this, R.font.dk_butterfly_ball)
        val textLogo: TextView = findViewById(R.id.txt_logo)
        textLogo.typeface = fontDK

        // Variaveis
        var edtLoginEmail = findViewById<TextInputEditText>(R.id.edt_loginEmailInput)
        val btnSignUp = findViewById<Button>(R.id.btn_signUp)

        btnSignUp.setOnClickListener {
            finish()
        }
    }
}