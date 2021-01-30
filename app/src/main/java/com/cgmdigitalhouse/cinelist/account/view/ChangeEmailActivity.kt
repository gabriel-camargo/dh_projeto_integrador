package com.cgmdigitalhouse.cinelist.account.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.cgmdigitalhouse.cinelist.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ChangeEmailActivity : AppCompatActivity() {

    private lateinit var _edtEmail: TextInputEditText
    private lateinit var _edtNewEmail: TextInputEditText
    private lateinit var _email: String
    private lateinit var _newEmail: String
    private  lateinit var _btnChangeEmail: Button
    private lateinit var _auth: FirebaseAuth
    private lateinit var _currentUser: FirebaseUser?

    private lateinit var _currentEmail: String
    private lateinit var  _currentPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_email)

        _edtEmail = findViewById(R.id.edt_emailChangeEmailInput)
        _edtNewEmail = findViewById(R.id.edt_newEmailChangeEmailInput)
        _btnChangeEmail = findViewById(R.id.btn_changeEmail)
        _email = _edtEmail.text.toString()
        _newEmail = _edtEmail.text.toString()
        _auth = Firebase.auth
        _currentUser = _auth.currentUser
        _currentEmail = _currentUser?.email.toString()
        _currentPassword = _currentUser?

        _btnChangeEmail.setOnClickListener {

            //_auth.signInWithEmailAndPassword(_newEmail, )
           // _auth.signInWithCredential(_auth.currentUser.providerId)

            _auth.currentUser?.updateEmail(_newEmail)
                ?.addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this,"E-mail alterado com sucesso.",Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}