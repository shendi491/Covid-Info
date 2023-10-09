package com.broadbandmultimedia.covidinfo.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.broadbandmultimedia.covidinfo.R
import com.broadbandmultimedia.covidinfo.model.rs.covid.ModelHospitalCvd
import com.broadbandmultimedia.covidinfo.utils.Constant
import com.broadbandmultimedia.covidinfo.view.activities.DetailHospitalsActivity
import kotlinx.android.synthetic.main.list_item_hospital_covid.view.*
import java.util.*


class HospitalCAdapter(private val context: Context) :
    RecyclerView.Adapter<HospitalCAdapter.HospitalsViewHolder>() {

    private val modelHospitalCArrayList = ArrayList<ModelHospitalCvd.ModelHospitalC>()

    fun setHospitalAdapter(items: ArrayList<ModelHospitalCvd.ModelHospitalC>) {
        modelHospitalCArrayList.clear()
        modelHospitalCArrayList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_hospital_covid, parent, false)
        return HospitalsViewHolder(view)
    }

    override fun onBindViewHolder(holder: HospitalsViewHolder, position: Int) {

        //set data to share & intent
        holder.tvName.text = modelHospitalCArrayList[position].name
        holder.tvAddress.text = modelHospitalCArrayList[position].address
        holder.tvTimeUpdate.text = modelHospitalCArrayList[position].info

        if (modelHospitalCArrayList[position].phone == null) {
            holder.tvPhone.text = "-"
        } else if (modelHospitalCArrayList[position].phone == modelHospitalCArrayList[position].phone) {
            holder.tvPhone.text = modelHospitalCArrayList[position].phone
        }

        if (modelHospitalCArrayList[position].queue == "0") {
            holder.tvQueue.text = "Saat ini tidak ada antrian"
        } else if (modelHospitalCArrayList[position].queue == modelHospitalCArrayList[position].queue) {
            holder.tvQueue.text = modelHospitalCArrayList[position].queue
            holder.tvQueue.setTextColor(ContextCompat.getColor(context, R.color.colorRed))
        }

        if (modelHospitalCArrayList[position].bedAvailability == "0") {
            holder.tvBedAvailability.text = "Saat ini tidak ada kamar kosong"
            holder.tvBedAvailability.setTextColor(ContextCompat.getColor(context, R.color.colorRed))
        } else if (modelHospitalCArrayList[position]
                .bedAvailability == modelHospitalCArrayList[position].bedAvailability) {
            holder.tvBedAvailability.text = modelHospitalCArrayList[position].bedAvailability
        }

        holder.cvDaftarRs.setOnClickListener {
            Constant.hospitalId = modelHospitalCArrayList[position].id
            Constant.hospitalName = modelHospitalCArrayList[position].name
            Constant.phoneNumber = modelHospitalCArrayList[position].phone.toString()
            val intent = Intent(context, DetailHospitalsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return modelHospitalCArrayList.size
    }

    class HospitalsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cvDaftarRs: CardView
        var tvName: TextView
        var tvAddress: TextView
        var tvPhone: TextView
        var tvQueue: TextView
        var tvBedAvailability: TextView
        var tvTimeUpdate: TextView

        init {
            cvDaftarRs = itemView.cvDaftarRs
            tvName = itemView.tvName
            tvAddress = itemView.tvAddress
            tvPhone = itemView.tvPhone
            tvQueue = itemView.tvQueue
            tvBedAvailability = itemView.tvBedAvailability
            tvTimeUpdate = itemView.tvTimeUpdate
        }
    }

}