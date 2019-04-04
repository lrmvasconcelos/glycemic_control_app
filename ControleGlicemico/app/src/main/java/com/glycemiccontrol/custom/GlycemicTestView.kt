package com.glycemiccontrol.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.glycemiccontrol.R
import kotlinx.android.synthetic.main.item_test.view.*

class GlycemicTestView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attributeSet, defStyle) {

    init {
        LayoutInflater.from(context).inflate(R.layout.item_test, this, false)
    }

    fun make(position: Int, model: GlycemicModel, callback: OnItemClick) {

        model.testValue?.apply {
            teste_value.text = this
        }

        model.description?.apply {
            description.text = this
        }

        model.data?.apply {
            data.text = this
        }

        card.setOnClickListener {
            callback.onItemClick(position, model)
        }

    }

    interface OnItemClick {
        fun onItemClick(position: Int, model: GlycemicModel)
    }

}