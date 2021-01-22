package com.cgmdigitalhouse.cinelist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.cgmdigitalhouse.cinelist.db.AppDatabase
import com.cgmdigitalhouse.cinelist.favoritemovies.movielist.model.MovieListModel
import com.cgmdigitalhouse.cinelist.favoritemovies.movielist.repository.MovieListRepository
import com.cgmdigitalhouse.cinelist.favoritemovies.movielist.viewmodel.MovieListViewModel
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieEntity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var _movieListViewModel: MovieListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Fonte DK
        val fontDK = ResourcesCompat.getFont(this, R.font.dk_butterfly_ball)
        val textLogo: TextView = findViewById(R.id.txt_logo)
        textLogo.typeface = fontDK

        // Variaveis
        val edtLoginEmail = findViewById<TextInputEditText>(R.id.edt_loginEmailInput)
        val edtLoginPassword = findViewById<TextInputEditText>(R.id.edt_loginPasswordInput)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        val btnSignUpLogin = findViewById<Button>(R.id.btn_signUpLogin)

        _movieListViewModel = ViewModelProvider(
                this,
                MovieListViewModel.MovieListViewModelFactory(MovieListRepository(AppDatabase.getDatabase(this).listMovieDao()))
        ).get(MovieListViewModel::class.java)

        btnLogin.setOnClickListener {
            val edtEmail = findViewById<TextInputLayout>(R.id.edt_loginEmailLayout)
            val edtSenha = findViewById<TextInputLayout>(R.id.edt_loginPasswordLayout)

            val email = edtEmail.editText!!.text.toString().trim()
            val password = edtSenha.editText!!.text.toString().trim()

            when {
                email.isEmpty() -> {
                    Toast.makeText(this, "Preencha o campo de email!", Toast.LENGTH_SHORT).show()
                }
                password.isEmpty() -> {
                    Toast.makeText(this, "Preencha o campo de senha!", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener{
                            if(it.isSuccessful) {
                                _movieListViewModel.searchWatchList().observe(this, Observer{
                                    createWatchList(it[0].toInt())
                                })
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this, "Email ou senha incorretas!", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }


        }

        btnSignUpLogin.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
    fun createWatchList(count: Int){

        if(count == 0){
            _movieListViewModel.inserirListMovie("WatchList","Filmes que pretendo assistir").observe(this, Observer {
            })
        }
    }
}