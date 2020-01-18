package com.mxcsyounes.earthquaketracker.api.models.usgs

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Geometry(
    @Expose @SerializedName("type") val type: String,
    @Expose @SerializedName("coordinates") val coordinates: Array<Double>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Geometry

        if (type != other.type) return false
        if (!coordinates.contentEquals(other.coordinates)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + coordinates.contentHashCode()
        return result
    }
}