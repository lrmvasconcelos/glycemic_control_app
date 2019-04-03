package com.glycemiccontrol.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.afollestad.materialdialogs.DialogAction
import com.afollestad.materialdialogs.MaterialDialog
import com.glycemiccontrol.R
import com.glycemiccontrol.base.BaseActivity
import com.glycemiccontrol.models.Pacients
import com.glycemiccontrol.viewmodel.UserTypeViewModel
import kotlinx.android.synthetic.main.activity_user_type.*

class UserTypeActivity : BaseActivity() {

    private lateinit var viewModel: UserTypeViewModel

    private var pacient: Pacients? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_type)

        viewModel = ViewModelProviders.of(this).get(UserTypeViewModel::class.java)

        button_action_login.setOnClickListener {
            loadUsers(user_account_field.text.toString())
        }


    }

    private fun loadUsers(user: String) {

        showDialogProgress()

        viewModel.getAllPacients()

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
