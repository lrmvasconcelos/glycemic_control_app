package com.glycemiccontrol.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.glycemiccontrol.R
import com.glycemiccontrol.models.Test
import com.glycemiccontrol.util.Utils
import kotlinx.android.synthetic.main.item_test.view.*

class GlycemicTestView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attributeSet, defStyle) {

    init {
        LayoutInflater.from(context).inflate(R.layout.item_test, this, true)
    }

    fun make(position: Int, model: Test, callback: OnItemClick) {

        model.valorGlicemia?.apply {
            teste_value.text = "$this"
        }

        model.observacoes?.apply {
            description.text = this
        }

        model.dataAvaliacao?.apply {
            data.text = Utils.dateToString(this, "dd/MM/yyyy")
        }

        card.setOnClickListener {
            callback.onItemClick(position, model)
        }

    }

    interface OnItemClick {
        fun onItemClick(position: Int, model: Test)
    }

}