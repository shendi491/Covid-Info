package com.broadbandmultimedia.covidinfo.activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.broadbandmultimedia.covidinfo.R
import com.broadbandmultimedia.covidinfo.edit_profile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*

class profile : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    var database : FirebaseDatabase? = null
    var databaseReference : DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance("https://covid-info-38247-default-rtdb.asia-southeast1.firebasedatabase.app/")
        databaseReference = database?.reference!!.child("profile")

        val user = auth.currentUser

        if (user != null){
            if (user.photoUrl != null){
                Picasso.get().load(user.photoUrl).into(logo)
            }else{
                Picasso.get().load("https://picsum.photos/id/1019/5472/3648").into(logo)
            }

            loadProfile()
        }

        editProfile.setOnClickListener {
            startActivity(Intent(this@profile, edit_profile::class.java))
            finish()
        }
    }

    private fun loadProfile() {
        val user = auth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)

        userreference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                namalengkap.text = " "+snapshot.child("Full Name").value.toString()
                phoneNumber.text = " "+snapshot.child("Phone Number").value.toString()
                date.text = " "+snapshot.child("Date Of Birth").value.toString()
                address.text = " "+snapshot.child("Alamat").value.toString()
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}