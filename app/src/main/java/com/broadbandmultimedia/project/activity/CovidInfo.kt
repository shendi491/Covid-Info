package com.broadbandmultimedia.covidinfo.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.broadbandmultimedia.covidinfo.api.RetrofitClient
import com.broadbandmultimedia.covidinfo.IndonesiaResponse
import com.broadbandmultimedia.covidinfo.R
import com.broadbandmultimedia.covidinfo.activity.profile
import com.broadbandmultimedia.covidinfo.view.activities.ProvinceActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_covid_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CovidInfo : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnprofile : ImageView
    private lateinit var btnHospital : Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid_info)

        btnprofile = findViewById(R.id.openprofile)
        btnHospital = findViewById(R.id.btnHospital)

        btnprofile.setOnClickListener(this)
        btnHospital.setOnClickListener(this)

        auth= FirebaseAuth.getInstance()
        btnlogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this@CovidInfo, MainActivity::class.java))
            finish()
        }

        showIndonesia()
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.openprofile -> run{
                val IntentBiasa = Intent(this@CovidInfo, profile::class.java)
                startActivity(IntentBiasa)
            }
            R.id.btnHospital -> run{
                val IntentBiasa = Intent(this@CovidInfo, ProvinceActivity::class.java)
                startActivity(IntentBiasa)
            }
        }
    }


    private fun showIndonesia(){
        RetrofitClient.instance.getIndonesia().enqueue(object :
            Callback<ArrayList<IndonesiaResponse>> {
            override fun onFailure(call: Call<ArrayList<IndonesiaResponse>>, t: Throwable) {
                Toast.makeText(this@CovidInfo, "${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ArrayList<IndonesiaResponse>>,
                response: Response<ArrayList<IndonesiaResponse>>
            ) {
                val indonesiaResponse = response.body()?.get(0)
                val positive = indonesiaResponse?.positif
                val hospitalized = indonesiaResponse?.dirawat
                val recover = indonesiaResponse?.sembuh
                val death = indonesiaResponse?.meninggal

                val tvPositive: TextView = findViewById(R.id.tvPositive)
                val tvHospitalized: TextView = findViewById(R.id.tvHospitalized)
                val tvRecover: TextView = findViewById(R.id.tvRecover)
                val tvDeath: TextView = findViewById(R.id.tvDeath)

                tvPositive.text = positive
                tvHospitalized.text= hospitalized
                tvRecover.text= recover
                tvDeath.text= death
            }

        })
    }
}