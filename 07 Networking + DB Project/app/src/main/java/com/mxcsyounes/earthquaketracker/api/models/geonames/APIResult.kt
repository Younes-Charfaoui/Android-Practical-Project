package com.mxcsyounes.earthquaketracker.api.models.geonames

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class APIResult(
    @Expose @SerializedName("earthquakes") val earthquakes: List<Earthquake>
)