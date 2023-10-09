package com.broadbandmultimedia.covidinfo.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.broadbandmultimedia.covidinfo.R
import com.broadbandmultimedia.covidinfo.reset_password
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.register.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnregist: Button
    private lateinit var btnresetpw : TextView
    lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        btnregist = findViewById(R.id.btnregist)
        btnresetpw = findViewById(R.id.forgotPassword)

        btnregist.setOnClickListener(this)
        btnresetpw.setOnClickListener(this)

        auth = FirebaseAuth.getInstance()

        login()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnregist -> run {
                val IntentBiasa = Intent(this@MainActivity, MainActivity2::class.java)
                startActivity(IntentBiasa)
            }
            R.id.forgotPassword -> run {
                val IntentBiasa = Intent(this@MainActivity, reset_password::class.java)
                startActivity(IntentBiasa)
            }
        }
    }

    private fun login() {

        login.setOnClickListener {
            if (TextUtils.isEmpty(emailLogin.text.toString())) {
                emailLogin.setError("Please Enter Your Email")
                return@setOnClickListener
            } else if (TextUtils.isEmpty(passwordLogin.text.toString())) {
                passwordLogin.setError("Please Enter Your Password")
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(
                emailLogin.text.toString(),
                passwordLogin.text.toString()
            )
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        startActivity(Intent(this@MainActivity, CovidInfo::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Login Failed, Please Try Again ! ",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null){
            startActivity(Intent(this@MainActivity, CovidInfo::class.java))
            finish()
        }
    }
}