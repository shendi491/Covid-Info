package com.broadbandmultimedia.covidinfo.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.broadbandmultimedia.covidinfo.R
import com.broadbandmultimedia.covidinfo.model.rs.noncovid.ModelHospitalNCvd
import com.broadbandmultimedia.covidinfo.utils.Constant
import com.broadbandmultimedia.covidinfo.view.activities.DetailHospitalsActivity
import kotlinx.android.synthetic.main.list_item_hospital_noncovid.view.*
import java.util.*


class HospitalNCAdapter(private val context: Context) :
    RecyclerView.Adapter<HospitalNCAdapter.HospitalsViewHolder>() {

    private val modelHospitalNCovidArrayList = ArrayList<ModelHospitalNCvd.ModelHospitalNCovid>()

    fun setHospitalAdapter(items: ArrayList<ModelHospitalNCvd.ModelHospitalNCovid>) {
        modelHospitalNCovidArrayList.clear()
        modelHospitalNCovidArrayList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_hospital_noncovid, parent, false)
        return HospitalsViewHolder(view)
    }

    override fun onBindViewHolder(holder: HospitalsViewHolder, position: Int) {

        //set data to share & intent
        holder.tvName.text = modelHospitalNCovidArrayList[position].name
        holder.tvAddress.text = modelHospitalNCovidArrayList[position].address
        holder.tvTimeUpdate.text = modelHospitalNCovidArrayList[position].info

        if (modelHospitalNCovidArrayList[position].phone == null) {
            holder.tvPhone.text = "-"
        } else if (modelHospitalNCovidArrayList[position].phone == modelHospitalNCovidArrayList[position].phone) {
            holder.tvPhone.text = modelHospitalNCovidArrayList[position].phone
        }

        holder.cvDaftarRs.setOnClickListener {
            Constant.hospitalId = modelHospitalNCovidArrayList[position].id
            Constant.hospitalName = modelHospitalNCovidArrayList[position].name
            Constant.phoneNumber = modelHospitalNCovidArrayList[position].phone.toString()
            val intent = Intent(context, DetailHospitalsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return modelHospitalNCovidArrayList.size
    }

    class HospitalsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cvDaftarRs: CardView = itemView.cvDaftarRs
        var tvName: TextView = itemView.tvName
        var tvAddress: TextView = itemView.tvAddress
        var tvPhone: TextView = itemView.tvPhone
        var tvTimeUpdate: TextView = itemView.tvTimeUpdate

    }

}