package com.glycemiccontrol.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.glycemiccontrol.R
import com.glycemiccontrol.base.BaseActivity
import com.glycemiccontrol.viewmodel.UserTypeViewModel

class UserTypeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_type)

        val model = ViewModelProviders.of(this).get(UserTypeViewModel::class.java)

        model.teste("Teste")
    }
}
