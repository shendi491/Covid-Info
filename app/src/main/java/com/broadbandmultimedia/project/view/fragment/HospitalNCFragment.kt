package com.broadbandmultimedia.covidinfo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.broadbandmultimedia.covidinfo.R
import com.broadbandmultimedia.covidinfo.model.rs.noncovid.ModelHospitalNCvd
import com.broadbandmultimedia.covidinfo.utils.Constant
import com.broadbandmultimedia.covidinfo.view.adapter.HospitalNCAdapter
import com.broadbandmultimedia.covidinfo.viewmodel.PrimaryViewModel
import kotlinx.android.synthetic.main.fragment_hospital.*
import java.util.*

class HospitalNCFragment : Fragment() {

    lateinit var primaryViewModel: PrimaryViewModel
    lateinit var hospitalsNCAdapter: HospitalNCAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hospital, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hospitalsNCAdapter = HospitalNCAdapter(requireContext())
        rvDaftarRs.layoutManager = LinearLayoutManager(context)
        rvDaftarRs.adapter = hospitalsNCAdapter
        rvDaftarRs.setHasFixedSize(true)
        linearNoData.visibility = View.GONE

        //viewmodel
        primaryViewModel = ViewModelProvider(this,
            NewInstanceFactory()).get(PrimaryViewModel::class.java)
        primaryViewModel.setHospitalsNonCovid(Constant.provinceId, Constant.kotaId)
        progressBar.visibility = View.VISIBLE
        primaryViewModel.getHospitalsNonCovid().observe(viewLifecycleOwner, { modelHospital: ArrayList<ModelHospitalNCvd.ModelHospitalNCovid> ->
            if (modelHospital.size != 0) {
                hospitalsNCAdapter.setHospitalAdapter(modelHospital)
            } else {
                progressBar.visibility = View.GONE
                linearNoData.visibility = View.VISIBLE
                rvDaftarRs.visibility = View.GONE
            }
            progressBar.visibility = View.GONE
        })
    }

}