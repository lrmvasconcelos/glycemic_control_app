package com.glycemiccontrol.app

import android.app.Application
import com.glycemiccontrol.models.Doctor
import com.glycemiccontrol.models.UserType

class App : Application() {

    companion object {

        private var instance: App? = null

        private var userType: UserType? = null

        private var pacient: Doctor? = null

        fun getInstance(): App? {
            return instance
        }

        fun setUserType(userType: UserType){
            this.userType = userType
        }

        fun getUsarType() : UserType? {
            return this.userType
        }



    }

}
