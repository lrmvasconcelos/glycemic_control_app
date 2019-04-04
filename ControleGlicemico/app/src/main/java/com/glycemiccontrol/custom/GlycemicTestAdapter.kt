package com.glycemiccontrol.custom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.glycemiccontrol.R
import kotlinx.android.synthetic.main.test_view.view.*
import java.util.*

class GlycemicTestAdapter(
    private val items: MutableList<GlycemicModel> = ArrayList(),
    private val callback: GlycemicTestView.OnItemClick
) : RecyclerView.Adapter<GlycemicTestAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.test_view, parent, true)
        return ItemViewHolder(view); }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var item = items[position];
        with(holder.itemView) {
            glycemic_test_view.make(position, item, callback)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view)
}