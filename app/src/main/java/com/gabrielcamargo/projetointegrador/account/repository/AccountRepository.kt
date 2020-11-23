package com.gabrielcamargo.projetointegrador.account.repository

import android.content.Context
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.account.model.AccountModel

class AccountRepository(private val context: Context) {

    val account = AccountModel(R.drawable.photo1, "Exemplo Exemplo", "exemplo@exemplo.com", "exemplo")

    fun getAccount(callback: (account: AccountModel) -> Unit) {
        callback.invoke(account)
    }


}