package com.mxcsyounes.earthquaketracker.database.entity

import androidx.room.Entity


@Entity(tableName = "earthquakes")
class Earthquake {

    private val magnitude: String? = null

    private val longitude: Double = 0.toDouble()

    private val latitude: Double = 0.toDouble()

    private val place: String? = null

    private val depth: Double = 0.toDouble()

    private val time: String? = null

    private val url: String? = null

    private val significance: String? = null

    private val eventId: String? = null

    private val status: String? = null

    private val title: String? = null
}