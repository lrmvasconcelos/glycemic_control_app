package com.glycemiccontrol.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.glycemiccontrol.R
import com.glycemiccontrol.app.App
import kotlinx.android.synthetic.main.activity_doctor.*

class DoctorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor)

        docterName.text = App.getDoctor()?.nome + " " + App.getDoctor()?.sobrenome

        location.text = App.getDoctor()!!.localTrabalho

        cmr.text = App.getDoctor()!!.crm

        speciality.text = App.getDoctor()!!.atuacao

    }
}
