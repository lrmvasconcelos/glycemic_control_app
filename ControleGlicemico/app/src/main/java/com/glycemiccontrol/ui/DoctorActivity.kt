package com.glycemiccontrol.ui

import android.os.Bundle
import com.glycemiccontrol.R
import com.glycemiccontrol.app.App
import com.glycemiccontrol.base.BaseActivity
import com.glycemiccontrol.custom.PatientAdapter
import com.glycemiccontrol.custom.PatientCardView
import com.glycemiccontrol.models.Patient
import com.glycemiccontrol.service.RetrofitBase
import kotlinx.android.synthetic.main.activity_doctor.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class DoctorActivity : BaseActivity() {

    var patients = ArrayList<Patient>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor)

        docterName.text = App.getDoctor()?.nome + " " + App.getDoctor()?.sobrenome

        location.text = App.getDoctor()!!.localTrabalho

        cmr.text = App.getDoctor()!!.crm

        speciality.text = App.getDoctor()!!.atuacao

        getPatients()

    }

    private fun getPatients() {

        showDialogProgress()

        RetrofitBase
            .getInterfaceRetrofit()!!
            .getAllPacients()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                hideDialogProgress()
                getDoctorPatiens(response)
            }, { error ->
                hideDialogProgress()
            })
    }

    private fun getDoctorPatiens(list: List<Patient>) {
        if (list != null && !list.isEmpty())
            list.forEach {
                if (it.medico?.crm.equals(App.getDoctor()?.crm))
                    patients?.add(it)
            }

        showPatients()

    }

    private fun showPatients() {
        if (!patients.isEmpty()) {
            recyclerView.adapter = PatientAdapter(patients, object : PatientCardView.OnItemClick {
                override fun onItemClick(position: Int, model: Patient) {
                }
            })
        }
    }
}
