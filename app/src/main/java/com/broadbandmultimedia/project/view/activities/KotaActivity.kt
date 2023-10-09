package com.broadbandmultimedia.covidinfo.view.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.broadbandmultimedia.covidinfo.R
import com.broadbandmultimedia.covidinfo.model.kota.ModelKotaResult
import com.broadbandmultimedia.covidinfo.utils.Constant
import com.broadbandmultimedia.covidinfo.view.adapter.KotaAdapter
import com.broadbandmultimedia.covidinfo.viewmodel.PrimaryViewModel
import kotlinx.android.synthetic.main.activity_kota.*
import java.util.*

class KotaActivity : AppCompatActivity(){

    private lateinit var primaryViewModel: PrimaryViewModel
    private lateinit var kotaAdapter: KotaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kota)

        setSupportActionBar(toolbar)
        assert(supportActionBar != null)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        tvTitle.text = Constant.provinceName
        linearNoData.visibility = View.GONE

        kotaAdapter = KotaAdapter(this)
        rvDaftarKota.layoutManager = LinearLayoutManager(this)
        rvDaftarKota.adapter = kotaAdapter
        rvDaftarKota.setHasFixedSize(true)

        //viewmodel
        primaryViewModel = ViewModelProvider(this,
            NewInstanceFactory())[PrimaryViewModel::class.java]
        primaryViewModel.setKota(Constant.provinceId)
        progressBar.visibility = View.VISIBLE
        primaryViewModel.getKota().observe(this,{ modelKota: ArrayList<ModelKotaResult.ModelKota> ->
            if (modelKota.size != 0) {
                kotaAdapter.setKotaAdapter(modelKota)
            } else {
                progressBar.visibility = View.GONE
                linearNoData.visibility = View.VISIBLE
                rvDaftarKota.visibility = View.GONE
            }
            progressBar.visibility = View.GONE
        })
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}