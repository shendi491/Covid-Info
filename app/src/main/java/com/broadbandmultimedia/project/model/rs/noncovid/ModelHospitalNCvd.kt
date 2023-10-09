package com.broadbandmultimedia.covidinfo.model.rs.noncovid

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class ModelHospitalNCvd {
    @SerializedName("hospitals")
    lateinit var hospitals: List<ModelHospitalNCovid>

    inner class ModelHospitalNCovid : Serializable {
        @SerializedName("id")
        lateinit var id: String

        @SerializedName("name")
        lateinit var name: String

        @SerializedName("address")
        lateinit var address: String

        @SerializedName("phone")
        lateinit var phone: String

        @SerializedName("info")
        var info: String? = null
    }
}