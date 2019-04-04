package com.glycemiccontrol.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.glycemiccontrol.R
import com.glycemiccontrol.base.BaseActivity
import com.glycemiccontrol.custom.GlycemicModel
import com.glycemiccontrol.custom.GlycemicTestAdapter
import com.glycemiccontrol.custom.GlycemicTestView
import com.glycemiccontrol.viewmodel.PatientViewModel
import kotlinx.android.synthetic.main.activity_patient.*
import java.util.*

class PatientActivity : BaseActivity() {

    private lateinit var viewModel: PatientViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient)

        viewModel = ViewModelProviders.of(this).get(PatientViewModel::class.java)

        recyclerView.adapter = GlycemicTestAdapter(Arrays.asList(
            GlycemicModel("90", "Almoço", "20/10/2019"),
            GlycemicModel("90", "Almoço", "20/10/2019"),
            GlycemicModel("90", "Almoço", "20/10/2019"),
            GlycemicModel("90", "Almoço", "20/10/2019"),
            GlycemicModel("90", "Almoço", "20/10/2019"),
            GlycemicModel("90", "Almoço", "20/10/2019")
        ), object : GlycemicTestView.OnItemClick{
            override fun onItemClick(position: Int, model: GlycemicModel) {
                Toast.makeText(applicationContext, " $position", Toast.LENGTH_SHORT).show()
            }
        })

    }
}
