package com.broadbandmultimedia.covidinfo.model.kota

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class ModelKotaResult {
    @SerializedName("cities")
    lateinit var cities: List<ModelKota>

    inner class ModelKota : Serializable {
        @SerializedName("id")
        lateinit var id: String

        @SerializedName("name")
        lateinit var name: String
    }

}