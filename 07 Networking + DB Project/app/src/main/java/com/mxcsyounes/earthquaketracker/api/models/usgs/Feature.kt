package com.mxcsyounes.earthquaketracker.api.models.usgs

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Feature(
    @Expose @SerializedName("type") val type: String,
    @Expose @SerializedName("properties") val properties: Property,
    @Expose @SerializedName("geometry") val geometry: Geometry,
    @Expose @SerializedName("id") val id: String
)