package com.cgmdigitalhouse.cinelist.account.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.cgmdigitalhouse.cinelist.R
import com.cgmdigitalhouse.cinelist.home.view.HomeFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class ChangeNameActivity : AppCompatActivity() {

    private lateinit var _edtName: TextInputEditText
    private  lateinit var _btnChangeName: Button
    private lateinit var _auth: FirebaseAuth
    private lateinit var _newName: String
    private lateinit var _currentUser: FirebaseUser
    private lateinit var _profileUpdates: UserProfileChangeRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_name)

        _edtName = findViewById(R.id.edt_nameChangeNameInput)
        _btnChangeName = findViewById(R.id.btn_changeName)
        _auth = Firebase.auth
        _currentUser = _auth.currentUser!!
        _newName = _edtName.editableText.toString()

        _btnChangeName.setOnClickListener {
            //updateName()
        }
    }

//    private fun updateName() {
//        _profileUpdates = userProfileChangeRequest {
//            displayName = _newName
//            photoUri = Uri.parse("teste")
//            // setDisplayName(_newName)
//        }
//
//        _currentUser.updateProfile(_profileUpdates)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    Toast.makeText(this,getString(R.string.nome_alterado_sucesso), Toast.LENGTH_SHORT).show()
//                    val transactionFragment = supportFragmentManager.beginTransaction()
//                    transactionFragment.add(R.id.frameLayout_mainActivity, AccountFragment())
//                    transactionFragment.commit()
//                    finish()
//                }
//            }
//    }
}