package com.glycemiccontrol.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.afollestad.materialdialogs.MaterialDialog
import com.glycemiccontrol.R
import com.glycemiccontrol.app.App
import com.glycemiccontrol.base.BaseActivity
import com.glycemiccontrol.custom.GlycemicModel
import com.glycemiccontrol.custom.GlycemicTestAdapter
import com.glycemiccontrol.custom.GlycemicTestView
import com.glycemiccontrol.models.Patient
import com.glycemiccontrol.models.Test
import com.glycemiccontrol.viewmodel.PatientViewModel
import kotlinx.android.synthetic.main.activity_patient.*
import java.util.*

class PatientActivity : BaseActivity() {

    private lateinit var viewModel: PatientViewModel

    private var patient: Patient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient)

        viewModel = ViewModelProviders.of(this).get(PatientViewModel::class.java)


    }

    override fun onResume() {
        super.onResume()

        recyclerView.adapter = null

        userName.text = App.getPacient()?.nome + " " + App.getPacient()?.sobrenome

        loadExams()
    }

    private fun loadExams(){

            showDialogProgress()

            viewModel.getAllPacients()

            viewModel.patientLiveData.observe(this, androidx.lifecycle.Observer { it ->

                hideDialogProgress()

                it.forEach {
                    if (it.matricula.equals(App.getPacient()?.matricula)) {
                        App.setPacient(it)
                        patient = it
                    }

                }

                if (patient != null && patient!!.exames != null){
                    setExams(patient!!.exames as MutableList<Test>)
                }

            })

            viewModel.errorLiveData.observe(this, androidx.lifecycle.Observer {
                hideDialogProgress()
                showSimpleDialog(" Ops", "Não foi possível encontrar usuário",
                    MaterialDialog.SingleButtonCallback { _, _ -> })
            })
    }

    private fun setExams(list: MutableList<Test>){
        recyclerView.adapter = GlycemicTestAdapter(list, object: GlycemicTestView.OnItemClick{
            override fun onItemClick(position: Int, model: Test) {

            }
        })
    }
}
