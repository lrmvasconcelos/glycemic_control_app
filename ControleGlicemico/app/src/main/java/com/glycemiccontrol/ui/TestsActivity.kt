package com.glycemiccontrol.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.glycemiccontrol.R
import com.glycemiccontrol.app.App
import com.glycemiccontrol.custom.GlycemicTestAdapter
import com.glycemiccontrol.custom.GlycemicTestView
import com.glycemiccontrol.models.Test
import kotlinx.android.synthetic.main.activity_tests.*

class TestsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tests)

        recyclerView.adapter = GlycemicTestAdapter(App.getPacient()?.exames as MutableList<Test>, object : GlycemicTestView.OnItemClick{
            override fun onItemClick(position: Int, model: Test) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}
