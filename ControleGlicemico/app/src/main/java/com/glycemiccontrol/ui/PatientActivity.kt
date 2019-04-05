package com.glycemiccontrol.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.afollestad.materialdialogs.MaterialDialog
import com.glycemiccontrol.R
import com.glycemiccontrol.app.App
import com.glycemiccontrol.base.BaseActivity
import com.glycemiccontrol.custom.DialogView
import com.glycemiccontrol.custom.GlycemicModel
import com.glycemiccontrol.custom.GlycemicTestAdapter
import com.glycemiccontrol.custom.GlycemicTestView
import com.glycemiccontrol.databinding.PartialAddExamDialogBinding.inflate
import com.glycemiccontrol.models.GlycemicTest
import com.glycemiccontrol.models.Patient
import com.glycemiccontrol.models.Test
import com.glycemiccontrol.service.RetrofitBase
import com.glycemiccontrol.viewmodel.PatientViewModel
import kotlinx.android.synthetic.main.activity_patient.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

class PatientActivity : BaseActivity() {

    private lateinit var viewModel: PatientViewModel

    private var patient: Patient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient)

        viewModel = ViewModelProviders.of(this).get(PatientViewModel::class.java)

        addTeste.setOnClickListener {
            showDialog()
        }


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

    fun showDialog(){

        val inflater = LayoutInflater.from(this)

        var dialogBinding = inflate(inflater)

        val dialogView = dialogBinding.root

        val dialogBuilder = DialogView.Builder(this)
            .title("Adicionar Teste")
            .descriptionGravity(Gravity.CENTER)
            .orientation(LinearLayout.HORIZONTAL)
            .customView(dialogView)
            .build()

        dialogBinding.addTeste.setOnClickListener {

            showDialogProgress()

            var request = GlycemicTest()

            request.setIdPaciente(App.getPacient()?.id!!)

            request.setObservacoes(dialogBinding.descriptionField.text.toString())

            request.setValorGlicemia(dialogBinding.testValueField.text.toString().toInt())

            RetrofitBase
                .getInterfaceRetrofit()!!
                .putExame(request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    hideDialogProgress()
                    dialogBuilder.dismiss()
                    loadExams()

                }, { error ->
                    hideDialogProgress()
                    showSimpleDialog(" Ops", "Erro ao adicionar exame",
                        MaterialDialog.SingleButtonCallback { _, _ -> })
                })


        }

        dialogBuilder.show(true)

    }
}
