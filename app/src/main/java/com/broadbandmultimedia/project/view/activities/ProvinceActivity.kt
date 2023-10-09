package com.broadbandmultimedia.covidinfo.view.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.broadbandmultimedia.covidinfo.R
import com.broadbandmultimedia.covidinfo.model.provinsi.ModelProvinsiResult
import com.broadbandmultimedia.covidinfo.viewmodel.PrimaryViewModel
import com.broadbandmultimedia.rumahsakit.view.adapter.ProvinceAdapter
import kotlinx.android.synthetic.main.activity_province.*
import java.util.*

class ProvinceActivity : AppCompatActivity() {

    lateinit var primaryViewModel : PrimaryViewModel
    lateinit var provinsiAdapter: ProvinceAdapter
    var REQ_PERMISSION = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_province)

        setStatusBar()
        setPermission()

        linearNoData.visibility = View.GONE

        provinsiAdapter = ProvinceAdapter(this)
        rvDaftarProvinsi.setLayoutManager(LinearLayoutManager(this))
        rvDaftarProvinsi.setAdapter(provinsiAdapter)
        rvDaftarProvinsi.setHasFixedSize(true)

        //viewmodel
        getProvinsiViewModel()
    }

    fun getProvinsiViewModel() {
        primaryViewModel = ViewModelProvider(this,
            NewInstanceFactory()).get(PrimaryViewModel::class.java)
        primaryViewModel.setProvinsi()
        progressBar.visibility = View.VISIBLE
        primaryViewModel.getProvinsi().observe(this,{modelProvinsi:ArrayList<ModelProvinsiResult.ModelProvinsi> ->
            if (modelProvinsi.size != 0) {
                provinsiAdapter.setProvinceAdapter(modelProvinsi)
            } else {
                progressBar.visibility = View.GONE
                linearNoData.visibility = View.VISIBLE
                rvDaftarProvinsi.visibility = View.GONE
            }
            progressBar.visibility = View.GONE
        })

    }

    private fun setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION), REQ_PERMISSION)
            return
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (grantResult in grantResults) {
            if (grantResult == PackageManager.PERMISSION_GRANTED) {
                val intent = intent
                finish()
                startActivity(intent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_PERMISSION && resultCode == RESULT_OK) {
            getProvinsiViewModel()
        }
    }

    companion object {
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val window = activity.window
            val layoutParams = window.attributes
            if (on) {
                layoutParams.flags = layoutParams.flags or bits
            } else {
                layoutParams.flags = layoutParams.flags and bits.inv()
            }
            window.attributes = layoutParams
        }
    }

}