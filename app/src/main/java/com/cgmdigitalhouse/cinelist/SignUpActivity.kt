package com.cgmdigitalhouse.cinelist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private lateinit var _auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        _auth = Firebase.auth

        // Fonte DK
        val fontDK = ResourcesCompat.getFont(this, R.font.dk_butterfly_ball)
        val textLogo: TextView = findViewById(R.id.txt_logo)
        textLogo.typeface = fontDK

        val btnSignUp = findViewById<Button>(R.id.btn_signUp)
        btnSignUp.setOnClickListener {
            val edtName = findViewById<TextInputLayout>(R.id.edt_singupNomeLayout)
            val edtLoginEmail = findViewById<TextInputLayout>(R.id.edt_singupEmailLayout)
            val edtPassword = findViewById<TextInputLayout>(R.id.edt_singupPasswordLayout)
            val edtConfirmPassword =
                findViewById<TextInputLayout>(R.id.edt_singupPasswordConfirmacaoLayout)

            edtName.error = null
            edtLoginEmail.error = null
            edtPassword.error = null
            edtConfirmPassword.error = null

            val name = edtName.editText!!.text.toString().trim()
            val email = edtLoginEmail.editText!!.text.toString().trim()
            val password = edtPassword.editText!!.text.toString().trim()
            val passwordConfirm = edtConfirmPassword.editText!!.text.toString().trim()

            when {
                name.isEmpty() -> {
                    edtName.error = "Preencha o campo de nome!"
                }
                email.isEmpty() -> {
                    edtLoginEmail.error = "Preencha o campo de e-mail!"
                }
                password.isEmpty() -> {
                    edtPassword.error = "Preencha o campo de senha!"
                }
                password != passwordConfirm -> {
                    edtConfirmPassword.error = "As senhas fornecidas não correspondem!"
                }
                else -> {
                    _auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                val profileUpdates = UserProfileChangeRequest.Builder()
                                    .setDisplayName(name).build()

                                _auth.currentUser!!.updateProfile(profileUpdates)

                                Snackbar.make(
                                    findViewById(android.R.id.content),
                                    "Usuário cadastrado com sucesso!",
                                    Snackbar.LENGTH_SHORT
                                ).show()

                                val intent = Intent(this, MainActivity::class.java)
                                intent.apply {
                                    putExtra("provider", ProviderType.BASIC)
                                }
                                startActivity(intent)
                                finish()

                            } else {
                                Snackbar.make(
                                    findViewById(android.R.id.content),
                                    "Erro ao cadastrar usuário!",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }
        }
    }
}