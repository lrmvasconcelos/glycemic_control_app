package com.glycemiccontrol.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.glycemiccontrol.R
import com.glycemiccontrol.app.App
import com.glycemiccontrol.base.BaseActivity
import com.glycemiccontrol.models.UserType
import kotlinx.android.synthetic.main.activity_user_type.*

class UserTypeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_type)
        btn_doctor.setOnClickListener {
            App.setUserType(UserType.MEDICO)
            openLoginActivity()
        }

        btn_pacient.setOnClickListener {
            App.setUserType(UserType.PACIENTE)
            openLoginActivity()
        }
    }

    private fun openLoginActivity(){
        openActivity(PatientActivity::class.java)
    }
}
