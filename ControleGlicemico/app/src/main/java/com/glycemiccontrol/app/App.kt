package com.glycemiccontrol.app

import android.app.Application
import com.glycemiccontrol.models.Doctor
import com.glycemiccontrol.models.Patient
import com.glycemiccontrol.models.UserType

class App : Application() {

    companion object {

        private var instance: App? = null

        private var userType: UserType? = null

        private var doctor: Doctor? = null

        private var patient: Patient? = null

        fun getInstance(): App? {
            return instance
        }

        fun setUserType(userType: UserType) {
            this.userType = userType
        }

        fun getUsarType(): UserType? {
            return this.userType
        }

        fun setPacient(patient: Patient) {
            this.patient = patient
        }

        fun getPacient(): Patient? {
            return this.patient
        }

        fun setDoctor(doctor: Doctor) {
            this.doctor = doctor
        }

        fun getDoctor(): Doctor? {
            return this.doctor
        }


    }

}
