package com.glycemiccontrol.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.afollestad.materialdialogs.MaterialDialog
import com.glycemiccontrol.R
import com.glycemiccontrol.base.BaseActivity
import com.glycemiccontrol.models.Doctor
import com.glycemiccontrol.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    private lateinit var viewModel: LoginViewModel

    private var pacient: Doctor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)


        button_action_login.setOnClickListener {
            loadUsers(user_account_field.text.toString())
        }
    }

    private fun loadUsers(user: String) {

        showDialogProgress()

        viewModel.getAllDoctors()

        viewModel.userLiveData.observe(this, Observer {

            it.forEach {
                if (it.matricula.equals(user))
                    pacient = it

            }

            hideDialogProgress()
            if (pacient == null)
                showSimpleDialog(" Ops","Não foi possível encontrar usuário" ,
                    MaterialDialog.SingleButtonCallback { _, _ -> })
        })

        viewModel.errorLiveData.observe(this, Observer {
            showSimpleDialog(" Ops","Não foi possível encontrar usuário" ,
                MaterialDialog.SingleButtonCallback { _, _ -> })
        })
    }
}
