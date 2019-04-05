package com.glycemiccontrol.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.glycemiccontrol.R
import com.glycemiccontrol.models.Patient
import kotlinx.android.synthetic.main.item_patient.view.*

class PatientCardView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attributeSet, defStyle) {

    init {
        LayoutInflater.from(context).inflate(R.layout.item_patient, this, true)
    }

    fun make(position: Int, patient: Patient, callback: OnItemClick) {

        userName.text = patient.nome + " " + patient.sobrenome

        patient_card.setOnClickListener {
            callback.onItemClick(position, patient)
        }

    }

    interface OnItemClick {
        fun onItemClick(position: Int, model: Patient)
    }

}