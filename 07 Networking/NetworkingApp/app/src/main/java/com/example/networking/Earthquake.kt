package com.example.networking

/**
 * Created by Gosu on 2017-02-19.
 */
class Earthquake//constructor getting magnitude, place and date of the earthquake
    (
    private val mag: Double,
    private val place: String,
    private val date: String,
    private val time: String,
    private val url: String
) {

    fun returnMag(): Double {
        return mag
    }

    fun returnPlace(): String {
        return place
    }

    fun returnTime(): String {
        return time
    }

    fun returnUrl(): String {
        return url
    }

    fun returnDate(): String {
        return date
    }
}
