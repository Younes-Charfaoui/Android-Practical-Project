package com.mxcsyounes.earthquaketracker.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class APIResult(
    @Expose @SerializedName("type") val type: String?,
    @Expose @SerializedName("metadata") val metadata: Metadata?,
    @Expose @SerializedName("features") val features: Feature?,
    @Expose @SerializedName("bbox") val bbox: Array<Double>
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as APIResult

        if (type != other.type) return false
        if (metadata != other.metadata) return false
        if (features != other.features) return false
        if (!bbox.contentEquals(other.bbox)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type?.hashCode() ?: 0
        result = 31 * result + (metadata?.hashCode() ?: 0)
        result = 31 * result + (features?.hashCode() ?: 0)
        result = 31 * result + bbox.contentHashCode()
        return result
    }
}