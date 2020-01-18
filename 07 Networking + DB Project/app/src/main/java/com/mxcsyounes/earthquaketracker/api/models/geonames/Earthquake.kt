package com.mxcsyounes.earthquaketracker.api.models.geonames

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "earthquakes")
data class Earthquake(

    @Expose @SerializedName("datetime") val datetime: String,
    @Expose @SerializedName("depth") val depth: Double,

    @Expose @SerializedName("lng") val lng: Double,
    @Expose @SerializedName("lat") val lat: Double,
    @Expose @SerializedName("magnitude") val magnitude: Double,
    @Expose @SerializedName("src") val src: String,

    @PrimaryKey
    @Expose @SerializedName("eqid") val eqid: String
)