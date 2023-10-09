package com.broadbandmultimedia.rumahsakit.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.broadbandmultimedia.covidinfo.R
import com.broadbandmultimedia.covidinfo.model.provinsi.ModelProvinsiResult
import com.broadbandmultimedia.covidinfo.utils.Constant
import com.broadbandmultimedia.covidinfo.view.activities.KotaActivity
import kotlinx.android.synthetic.main.list_item_provinsi.view.*
import java.util.*


class ProvinceAdapter(private val context: Context) :
    RecyclerView.Adapter<ProvinceAdapter.ProvinsiViewHolder>() {

    private val modelProvinsiArrayList = ArrayList<ModelProvinsiResult.ModelProvinsi>()

    fun setProvinceAdapter(items: ArrayList<ModelProvinsiResult.ModelProvinsi>) {
        modelProvinsiArrayList.clear()
        modelProvinsiArrayList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinsiViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_provinsi, parent, false)
        return ProvinsiViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProvinsiViewHolder, position: Int) {

        //set data to share & intent
        holder.tvProvinsi.text = modelProvinsiArrayList[position].name

        holder.cvDaftarProvinsi.setOnClickListener {
            Constant.provinceId = modelProvinsiArrayList[position].id
            Constant.provinceName = modelProvinsiArrayList[position].name
            val intent = Intent(context, KotaActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return modelProvinsiArrayList.size
    }

    class ProvinsiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cvDaftarProvinsi: CardView
        var tvProvinsi: TextView

        init {
            cvDaftarProvinsi = itemView.cvDaftarProvinsi
            tvProvinsi = itemView.tvProvinsi
        }
    }

}