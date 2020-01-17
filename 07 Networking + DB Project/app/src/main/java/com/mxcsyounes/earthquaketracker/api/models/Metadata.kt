package com.mxcsyounes.earthquaketracker.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Metadata(
    @Expose @SerializedName("generated") val generated: Int,
    @Expose @SerializedName("url") val url: String,
    @Expose @SerializedName("title") val title: String,
    @Expose @SerializedName("status") val status: Int,
    @Expose @SerializedName("api") val api: String,
    @Expose @SerializedName("count") val count: Int
)