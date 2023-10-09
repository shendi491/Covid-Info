package com.broadbandmultimedia.rumahsakit.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.broadbandmultimedia.covidinfo.R
import com.broadbandmultimedia.covidinfo.model.rs.covid.ModelHospitalCvd
import com.broadbandmultimedia.covidinfo.utils.Constant
import com.broadbandmultimedia.covidinfo.view.adapter.HospitalCAdapter
import com.broadbandmultimedia.covidinfo.viewmodel.PrimaryViewModel
import kotlinx.android.synthetic.main.fragment_hospital.*
import java.util.*

class HospitalCFragment : Fragment() {

    lateinit var primaryViewModel: PrimaryViewModel
    lateinit var hospitalsCAdapter: HospitalCAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hospital, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hospitalsCAdapter = HospitalCAdapter(requireContext())
        rvDaftarRs.layoutManager = LinearLayoutManager(context)
        rvDaftarRs.adapter = hospitalsCAdapter
        rvDaftarRs.setHasFixedSize(true)
        linearNoData.visibility = View.GONE

        //viewmodel
        primaryViewModel = ViewModelProvider(this,
            NewInstanceFactory()).get(PrimaryViewModel::class.java)
        primaryViewModel.setHospitalsCovid(Constant.provinceId, Constant.kotaId)
        progressBar.visibility = View.VISIBLE
        primaryViewModel.getHospitalsCovid().observe(viewLifecycleOwner, { modelHospital: ArrayList<ModelHospitalCvd.ModelHospitalC> ->
            if (modelHospital.size != 0) {
                hospitalsCAdapter.setHospitalAdapter(modelHospital)
            } else {
                progressBar.visibility = View.GONE
                linearNoData.visibility = View.VISIBLE
                rvDaftarRs.visibility = View.GONE
            }
            progressBar.visibility = View.GONE
        })
    }

}