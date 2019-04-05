package com.glycemiccontrol.custom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.glycemiccontrol.R
import com.glycemiccontrol.models.Patient
import kotlinx.android.synthetic.main.patient_view.view.*
import java.util.*

class PatientAdapter(
    private val items: MutableList<Patient> = ArrayList(),
    private val callback: PatientCardView.OnItemClick
) : RecyclerView.Adapter<PatientAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.patient_view, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PatientAdapter.ItemViewHolder, position: Int) {
        var item = items[position];
        with(holder.itemView) {
            patientCard.make(position, item, callback)
        }
    }

    class ItemViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view)

}