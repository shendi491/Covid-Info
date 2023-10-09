package com.broadbandmultimedia.covidinfo.model.rs.covid

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class ModelHospitalCvd {
    @SerializedName("hospitals")
    lateinit var hospitals: List<ModelHospitalC>

    inner class ModelHospitalC : Serializable {
        @SerializedName("id")
        lateinit var id: String

        @SerializedName("name")
        lateinit var name: String

        @SerializedName("address")
        lateinit var address: String

        @SerializedName("phone")
        var phone: String? = null

        @SerializedName("queue")
        lateinit var queue: String

        @SerializedName("bed_availability")
        lateinit var bedAvailability: String

        @SerializedName("info")
        var info: String? = null
    }

}