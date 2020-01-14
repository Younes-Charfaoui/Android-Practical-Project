package com.example.networking

import android.util.Log

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date

object QueryUtils {

    // Returns URL from the given String
    fun createUrl(stringUrl: String): URL? {
        var url: URL? = null
        try {
            url = URL(stringUrl)
        } catch (exception: MalformedURLException) {
            Log.e("Error with creating URL", exception.toString())
            return null
        }

        return url
    }

    // Establishing connection with the chosen URL
    @Throws(IOException::class)
    fun makeHttpRequest(url: URL?): String {
        var jsonResponse = ""
        var urlConnection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        if (url == null)
            return jsonResponse

        try {
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.readTimeout = 10000
            urlConnection.connectTimeout = 15000
            urlConnection.connect()
            if (urlConnection.responseCode == 200) {
                inputStream = urlConnection.inputStream
                jsonResponse = readFromStream(inputStream)
            } else
                Log.e("httpError", "responseCode is not 200")
        } catch (e: IOException) {
            Log.e("httpError", e.toString())
        } finally {
            urlConnection?.disconnect()
            inputStream?.close()
        }

        return jsonResponse
    }

    /* Function created for parsing data from JSON, then adding new Earthquake object
       with the specified parameters to the ArrayList
    */
    @Throws(IOException::class)
    fun extractEarthquakes(jsonResponse: String): ArrayList<Earthquake> {

        // Create an empty ArrayList that we can add earthquakes to
        val earthquakes = ArrayList<Earthquake>()

        // Parse data from the JSON
        try {
            val reader = JSONObject(jsonResponse)
            val features = reader.getJSONArray("features")

            for (i in 0 until features.length()) {
                val f = features.getJSONObject(i)
                val prop = f.getJSONObject("properties")
                val mag = prop.getDouble("mag")
                val place = prop.getString("place")
                val time = prop.getLong("time")
                val url = prop.getString("url")

                //convert milliseconds to actual date
                val dateObject = Date(time)
                val dateFormatter = SimpleDateFormat("MMM dd, yyyy")
                val timeFormatter = SimpleDateFormat("h:mm a")
                val dateToDisplay = dateFormatter.format(dateObject)
                val timeToDisplay = timeFormatter.format(dateObject)
                earthquakes.add(Earthquake(mag, place, dateToDisplay, timeToDisplay, url))
            }

        } catch (e: JSONException) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e)
        }

        // Return the list of earthquakes
        return earthquakes
    }

    // Function used to read the whole information and put in in the one String
    @Throws(IOException::class)
    private fun readFromStream(inputStream: InputStream?): String {
        val output = StringBuilder()
        if (inputStream != null) {
            val inputStreamReader = InputStreamReader(inputStream, Charset.forName("UTF-8"))
            val reader = BufferedReader(inputStreamReader)
            var line: String? = reader.readLine()
            while (line != null) {
                output.append(line)
                line = reader.readLine()
            }
        }
        return output.toString()
    }

}