package com.broadbandmultimedia.covidinfo.activity

import com.google.firebase.database.Exclude

data class User(var fullname : String?, var phone : String?, var tanggal : String?, var alamat : String?){
    @Exclude
    fun getMap(): Map<String, Any?> {
        return mapOf(
            "Full Name" to fullname,
            "Phone Number" to phone,
            "Date Of Birth" to tanggal,
            "Alamat" to alamat
        )
    }
}