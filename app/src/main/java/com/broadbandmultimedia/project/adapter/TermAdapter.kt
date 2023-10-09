package com.broadbandmultimedia.covidinfo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.broadbandmultimedia.covidinfo.holder
import com.broadbandmultimedia.covidinfo.item

class TermAdapter (private val data: ArrayList<item>):RecyclerView.Adapter<holder>() {
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: holder, position: Int) {
        holder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        val inflater=LayoutInflater.from(parent.context)
        return holder (inflater,parent)
    }
}