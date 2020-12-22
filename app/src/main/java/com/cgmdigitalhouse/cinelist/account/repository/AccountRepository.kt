package com.cgmdigitalhouse.cinelist.account.repository

import android.content.Context
import com.cgmdigitalhouse.cinelist.R
import com.cgmdigitalhouse.cinelist.account.model.AccountModel

class AccountRepository(private val context: Context) {

    val account = AccountModel(R.drawable.profile_picture, "Nome de Exemplo", "email@exemplo.com", "exemplo")

    fun getAccount(callback: (account: AccountModel) -> Unit) {
        callback.invoke(account)
    }


}