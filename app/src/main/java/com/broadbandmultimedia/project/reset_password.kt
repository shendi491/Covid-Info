package com.broadbandmultimedia.covidinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.broadbandmultimedia.covidinfo.activity.MainActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_reset_password.*
import kotlinx.android.synthetic.main.login.*

class reset_password : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        btnreset.setOnClickListener {
            val email = emailReset.text.toString().trim()

            if (email.isEmpty()){
                emailReset.error = "Email harus diisi"
                emailReset.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                emailReset.error = "Email tidak valid"
                emailReset.requestFocus()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this, "Cek email untuk reset password", Toast.LENGTH_SHORT).show()
                    Intent(this@reset_password, MainActivity::class.java).also{
                        it.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                }else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}