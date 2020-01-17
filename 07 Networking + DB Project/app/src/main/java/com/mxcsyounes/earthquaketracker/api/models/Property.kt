package com.mxcsyounes.earthquaketracker.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Property(
    @Expose @SerializedName("mag") val mag: Int,
    @Expose @SerializedName("place") val place: String,
    @Expose @SerializedName("time") val time: Int,
    @Expose @SerializedName("updated") val updated: Int,
    @Expose @SerializedName("tz") val tz: Int,
    @Expose @SerializedName("url") val url: String,
    @Expose @SerializedName("detail") val detail: String,
    @Expose @SerializedName("status") val status: String,
    @Expose @SerializedName("tsunami") val tsunami: Int,
    @Expose @SerializedName("sig") val sig: Int,
    @Expose @SerializedName("net") val net: String,
    @Expose @SerializedName("code") val code: String,
    @Expose @SerializedName("ids") val ids: String,
    @Expose @SerializedName("sources") val sources: String,
    @Expose @SerializedName("types") val types: String,
    @Expose @SerializedName("nst") val nst: Int,
    @Expose @SerializedName("dmin") val dmin: Double,
    @Expose @SerializedName("rms") val rms: Double,
    @Expose @SerializedName("gap") val gap: Int,
    @Expose @SerializedName("magType") val magType: String,
    @Expose @SerializedName("type") val type: String,
    @Expose @SerializedName("title") val title: String
)