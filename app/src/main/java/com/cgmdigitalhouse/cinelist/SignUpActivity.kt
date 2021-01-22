package com.cgmdigitalhouse.cinelist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Fonte DK
        val fontDK = ResourcesCompat.getFont(this, R.font.dk_butterfly_ball)
        val textLogo: TextView = findViewById(R.id.txt_logo)
        textLogo.typeface = fontDK

        // Variaveis
        val btnSignUp = findViewById<Button>(R.id.btn_signUp)

        btnSignUp.setOnClickListener {
            val edtLoginEmail = findViewById<TextInputLayout>(R.id.edt_singupEmailLayout)
            val edtPassword = findViewById<TextInputLayout>(R.id.edt_singupPasswordLayout)
            val edtConfirmPassword = findViewById<TextInputLayout>(R.id.edt_singupPasswordConfirmacaoLayout)

            val email = edtLoginEmail.editText!!.text.toString().trim()
            val password = edtPassword.editText!!.text.toString().trim()
            val passwordConfirm = edtConfirmPassword.editText!!.text.toString().trim()

            when {
                email.isEmpty() -> {
                    Toast.makeText(this, "Preencha o campo de email!", Toast.LENGTH_SHORT).show()
                }
                password.isEmpty() -> {
                    Toast.makeText(this, "Preencha o campo de senha!", Toast.LENGTH_SHORT).show()
                }
                password != passwordConfirm -> {
                    Toast.makeText(this, "As senhas fornecidas são diferentes!", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener{
                            if(it.isSuccessful) {
                                Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()

                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this, "Erro ao cadastrar usuário", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }
        }
    }
}