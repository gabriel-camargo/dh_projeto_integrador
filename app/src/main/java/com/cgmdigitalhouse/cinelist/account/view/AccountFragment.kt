package com.cgmdigitalhouse.cinelist.account.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.cgmdigitalhouse.cinelist.LoginActivity
import com.cgmdigitalhouse.cinelist.R
import com.cgmdigitalhouse.cinelist.account.repository.AccountRepository
import com.cgmdigitalhouse.cinelist.account.viewmodel.AccountViewModel
import com.google.firebase.auth.FirebaseAuth

class AccountFragment : Fragment() {
    lateinit var _view: View
    private lateinit var _viewModel: AccountViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_account, container, false)
        return _view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewModel = ViewModelProvider(
            this,
            AccountViewModel.AccountViewModelFactory(AccountRepository(_view.context))
        ).get(AccountViewModel::class.java)

        _viewModel.account.observe(viewLifecycleOwner, Observer {
            val _image = _view.findViewById<ImageView>(R.id.img_usuario)
            val _name = _view.findViewById<TextView>(R.id.txt_nomeUsuario)
            val _email = _view.findViewById<TextView>(R.id.txt_emailUsuario)


            _name.text = it.name
            _email.text = it.email

            Glide.with(view.context)
                .load(it.image)
                .circleCrop()
                .into(_image)
        })

        _viewModel.getAccount()

        val btnSair = _view.findViewById<Button>(R.id.btn_sair)

        btnSair.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(_view.context, LoginActivity::class.java)
            startActivity(intent)
            activity!!.finish()
        }

    }

}