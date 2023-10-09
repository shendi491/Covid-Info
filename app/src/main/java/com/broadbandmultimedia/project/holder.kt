package com.broadbandmultimedia.covidinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.broadbandmultimedia.covidinfo.R.*

class holder (inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(
    layout.recyclerview,parent,false)){
    private var tnc: TextView? = null
    init {
        tnc=itemView.findViewById(id.tnc)
    }
    fun bind(data:item){
        tnc?.text=data.tnc
    }
}
