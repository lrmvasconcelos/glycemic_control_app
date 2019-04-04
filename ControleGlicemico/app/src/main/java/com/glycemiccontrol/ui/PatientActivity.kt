package com.glycemiccontrol.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.glycemiccontrol.R
import com.glycemiccontrol.base.BaseActivity
import com.glycemiccontrol.viewmodel.PatientViewModel

class PatientActivity : BaseActivity() {

    private lateinit var viewModel: PatientViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient)

        viewModel = ViewModelProviders.of(this).get(PatientViewModel::class.java)


    }
}
