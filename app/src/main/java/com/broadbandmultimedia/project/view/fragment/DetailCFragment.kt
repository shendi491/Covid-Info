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
import com.broadbandmultimedia.covidinfo.model.detail.ModelDetailResult
import com.broadbandmultimedia.covidinfo.utils.Constant
import com.broadbandmultimedia.covidinfo.view.adapter.DetailAdapter
import com.broadbandmultimedia.covidinfo.viewmodel.PrimaryViewModel
import kotlinx.android.synthetic.main.fragment_hospital.*
import java.util.*

class DetailCFragment : Fragment() {

    lateinit var primaryViewModel: PrimaryViewModel
    lateinit var detailAdapter: DetailAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hospital, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailAdapter = DetailAdapter()
        rvDaftarRs.layoutManager = LinearLayoutManager(context)
        rvDaftarRs.adapter = detailAdapter
        rvDaftarRs.setHasFixedSize(true)
        linearNoData.visibility = View.GONE

        //viewmodel
        primaryViewModel = ViewModelProvider(this,
            NewInstanceFactory()).get(PrimaryViewModel::class.java)
        primaryViewModel.setDetailsCovid(Constant.hospitalId)
        progressBar.setVisibility(View.VISIBLE)
        primaryViewModel.getDetailsCovid().observe(viewLifecycleOwner,{ modelHospital: ArrayList<ModelDetailResult.ModelData.BedDetail> ->
            if (modelHospital.size != 0) {
                detailAdapter.setDetailAdapter(modelHospital)
            } else {
                progressBar.setVisibility(View.GONE)
                linearNoData.setVisibility(View.VISIBLE)
                rvDaftarRs.setVisibility(View.GONE)
            }
            progressBar.setVisibility(View.GONE)
        })
    }

}