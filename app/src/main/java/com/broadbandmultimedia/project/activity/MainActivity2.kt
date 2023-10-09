package com.broadbandmultimedia.covidinfo.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.broadbandmultimedia.covidinfo.R
import com.broadbandmultimedia.covidinfo.adapter.TermAdapter
import com.broadbandmultimedia.covidinfo.item
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.register.*

class MainActivity2 : AppCompatActivity(), View.OnClickListener {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: TermAdapter
    lateinit var auth: FirebaseAuth
    var databaseReference : DatabaseReference? = null
    var database: FirebaseDatabase? = null

    private lateinit var btnacc : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance("https://covid-info-38247-default-rtdb.asia-southeast1.firebasedatabase.app/")
        databaseReference = database?.reference!!.child("profile")

        register()

        btnacc = findViewById(R.id.haveacc)
        btnacc.setOnClickListener(this)

        init()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
    override fun onClick(v: View){
        when(v.id){
            R.id.haveacc -> run {
                val IntentBiasa = Intent(this@MainActivity2, MainActivity::class.java)
                startActivity(IntentBiasa)
            }
        }
    }

    private fun register() {

        tomboladd.setOnClickListener {

            if (TextUtils.isEmpty(username.text.toString())) {
                username.setError("Please enter Your Username")
                return@setOnClickListener
            } else if (TextUtils.isEmpty(phone_number.text.toString())) {
                phone_number.setError("Please enter Your Phone Number")
                return@setOnClickListener
            }else if (TextUtils.isEmpty(name.text.toString())) {
                name.setError("Please enter Your Full Name")
                return@setOnClickListener
            } else if (TextUtils.isEmpty(dateofbirth.text.toString())) {
                dateofbirth.setError("Please enter Your Birth date")
                return@setOnClickListener
            }else if (TextUtils.isEmpty(alamat.text.toString())) {
                alamat.setError("Please enter Your Address")
                return@setOnClickListener
            } else if (TextUtils.isEmpty(email.text.toString())) {
                email.setError("Please enter Your Email")
                return@setOnClickListener
            } else if (TextUtils.isEmpty(password.text.toString())) {
                password.setError("Please enter Your Password")
                return@setOnClickListener
            }
            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        val currentUser = auth.currentUser
                        val currentUserdb = databaseReference?.child((currentUser?.uid!!))
                        currentUserdb?.child("Email")?.setValue(email.text.toString())
                        currentUserdb?.child("Password")?.setValue(password.text.toString())
                        currentUserdb?.child("Username")?.setValue(username.text.toString())
                        currentUserdb?.child("Phone Number")?.setValue(phone_number.text.toString())
                        currentUserdb?.child("Full Name")?.setValue(name.text.toString())
                        currentUserdb?.child("Date Of Birth")?.setValue(dateofbirth.text.toString())
                        currentUserdb?.child("Alamat")?.setValue(alamat.text.toString())
                        startActivity(Intent(this@MainActivity2, CovidInfo::class.java))
                        finish()
                        Toast.makeText(this@MainActivity2, "Registration Success, Welcome.", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this@MainActivity2, "Registration Failed, Please Try Again ! ", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
    private fun init() {
        recyclerView = findViewById(R.id.recyclerView)
        var data = arrayListOf<item>()
        data.add(item(tnc = "Term and Conditions \nThis page (together with the documents referred to in it) sets out the terms on which you may make use of this website https://tghn.org and any subdomains (the “site”), whether as a guest or a registered user. Use of the site includes accessing, browsing or registering to use the site.Please read these Terms of Use carefully before you start to use the site: by using the site, you confirm that you accept these terms of use and that you agree to comply with them. \n If you do not agree to these terms of use, you must not use the site."))
        data.add(item(tnc = "Information about us \nThe Global Health Network (“TGHN”) is a collaborative covidinfo involving research organisations from all over the world. \nThe site has been established to achieve TGHN’s aim to facilitate collaboration and resource sharing for global health. The site operates as a network, hosting communities of practice focusing on specific therapeutic areas and research issues. The University of Oxford (the “University”) is currently acting as the coordinator for the site infrastructure, but each community of practice is coordinated by a TGHN collaborator."))
        data.add(item(tnc = "Registration and passwords \nFrom time to time, the University may restrict access to some parts of the site, or the entire site, to users who have registered with it. \nIf you choose, or you are provided with, a user identification code, password or any other piece of information as part of the site’s security procedures, you must treat such information as confidential. You must not disclose it to any third party."))
        adapter = TermAdapter(data)
    }
}