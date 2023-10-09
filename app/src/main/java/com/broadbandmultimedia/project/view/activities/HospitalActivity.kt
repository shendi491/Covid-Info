package com.broadbandmultimedia.covidinfo.view.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.broadbandmultimedia.covidinfo.R
import com.broadbandmultimedia.covidinfo.utils.Constant
import com.broadbandmultimedia.covidinfo.view.adapter.HospitalsPagerAdapter
import kotlinx.android.synthetic.main.activity_hospital.*

class HospitalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital)

        setSupportActionBar(toolbar)
        assert(supportActionBar != null)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        tvTitle.text = Constant.kotaName

        viewPager.adapter = HospitalsPagerAdapter(supportFragmentManager)
        viewPager.offscreenPageLimit = 2
        tabsLayout.setupWithViewPager(viewPager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}