package com.glycemiccontrol.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.afollestad.materialdialogs.MaterialDialog
import com.glycemiccontrol.R
import com.glycemiccontrol.app.App
import com.glycemiccontrol.base.BaseActivity
import com.glycemiccontrol.models.Doctor
import com.glycemiccontrol.models.Patient
import com.glycemiccontrol.models.UserType
import com.glycemiccontrol.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    private lateinit var viewModel: LoginViewModel

    private var doctor: Doctor? = null

    private var patient: Patient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)


        button_action_login.setOnClickListener {
            if (isValidInput())
                if (App.getUsarType()!! == UserType.MEDICO)
                    loadDoctors(user_account_field.text.toString())
                else
                    loadPatients(user_account_field.text.toString())
        }
    }

    private fun isValidInput(): Boolean {

        var isvalid = true

        if (TextUtils.isEmpty(user_account_field.text)) {
            user_account.error = "Campo obrigatório"
            isvalid = false
        } else {
            user_account.error = null

        }

        if (TextUtils.isEmpty(user_password_field.text)) {
            user_password.error = "Campo obrigatório"
            isvalid = false
        } else {
            user_password.error = null

        }

        return isvalid

    }

    private fun loadDoctors(user: String) {

        showDialogProgress()

        viewModel.getAllDoctors()

        viewModel.doctorsLiveData.observe(this, Observer {

            it.forEach {
                if (it.matricula.equals(user)) {
                    App.setDoctor(it)
                    doctor = it
                }

            }

            hideDialogProgress()
            if (doctor == null)
                showSimpleDialog(" Ops", "Não foi possível encontrar usuário",
                    MaterialDialog.SingleButtonCallback { _, _ -> })
        })

        viewModel.errorLiveData.observe(this, Observer {
            showSimpleDialog(" Ops", "Não foi possível encontrar usuário",
                MaterialDialog.SingleButtonCallback { _, _ -> })
        })
    }

    private fun loadPatients(user: String) {

        showDialogProgress()

        viewModel.getAllPacients()

        viewModel.patientLiveData.observe(this, Observer { it ->

            it.forEach {
                if (it.matricula.equals(user)) {
                    App.setPacient(it)
                    patient = it
                    openActivityNewTask(PatientActivity::class.java)
                }

            }

            hideDialogProgress()
            if (patient == null)
                showSimpleDialog(" Ops", "Não foi possível encontrar usuário",
                    MaterialDialog.SingleButtonCallback { _, _ -> })
        })

        viewModel.errorLiveData.observe(this, Observer {
            hideDialogProgress()
            showSimpleDialog(" Ops", "Não foi possível encontrar usuário",
                MaterialDialog.SingleButtonCallback { _, _ -> })
        })
    }
}
