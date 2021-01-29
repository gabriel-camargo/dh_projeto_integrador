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
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {
    private lateinit var _movieListViewModel: MovieListViewModel
    private val GOOGLE_SIGN_IN = 100

    private val callbackManager = CallbackManager.Factory.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        FirebaseAuth.getInstance().signOut()
        LoginManager.getInstance().logOut()

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
                                intent.apply {
                                    putExtra("provider", ProviderType.BASIC)
                                }
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this, "Email ou senha incorretas!", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }


        }

        val btnGoogle = findViewById<Button>(R.id.btn_google)
        btnGoogle.setOnClickListener{
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()

            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
        }

        val btnFacebook = findViewById<Button>(R.id.btn_facebook)
        btnFacebook.setOnClickListener {

//            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))

            LoginManager.getInstance().registerCallback(callbackManager,
                object: FacebookCallback<LoginResult> {
                    override fun onSuccess(result: LoginResult?) {
                        val i = 1
                        result?.let {

                            val token = it.accessToken

                            val credential = FacebookAuthProvider.getCredential(token.token)

                            FirebaseAuth.getInstance().signInWithCredential(credential)
                                .addOnCompleteListener {
                                    if(it.isSuccessful) {
                                        _movieListViewModel.searchWatchList().observe(this@LoginActivity, Observer{
                                            createWatchList(it[0].toInt())
                                        })
                                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                        intent.apply {
                                            putExtra("provider", ProviderType.FACEBOOK)
                                        }
                                        startActivity(intent)
                                        finish()
                                    } else {
                                        Toast.makeText(this@LoginActivity, "Erro ao autenticar!", Toast.LENGTH_SHORT).show()
                                    }
                                }
                        }
                    }

                    override fun onCancel() {

                    }

                    override fun onError(error: FacebookException?) {
                        Toast.makeText(this@LoginActivity, "Erro ao autenticar!", Toast.LENGTH_SHORT).show()
                    }
                }
            )
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)

            try {
                if(account != null) {

                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener {
                            if(it.isSuccessful) {
                                _movieListViewModel.searchWatchList().observe(this, Observer{
                                    createWatchList(it[0].toInt())
                                })
                                val intent = Intent(this, MainActivity::class.java)
                                intent.apply {
                                    putExtra("provider", ProviderType.GOOGLE)
                                }
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this, "Erro ao autenticar!", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            } catch (e: ApiException) {
                Toast.makeText(this, "Erro ao autenticar!", Toast.LENGTH_SHORT).show()
            }


        }
    }
}