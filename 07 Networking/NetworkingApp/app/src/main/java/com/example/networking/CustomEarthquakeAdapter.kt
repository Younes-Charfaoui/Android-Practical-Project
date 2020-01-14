package com.example.networking

import android.content.Context
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView

import androidx.core.content.ContextCompat

import java.util.ArrayList

class CustomEarthquakeAdapter(internal var context: Context, objects: ArrayList<Earthquake>) :
    ArrayAdapter<Earthquake>(context, 0, objects) {
    internal var magnitudeColor: Int = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val inflater =
            getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        convertView = inflater.inflate(R.layout.my_list, null)

        //finding elements of the layout
        val listMain = convertView!!.findViewById<View>(R.id.listMain) as LinearLayout
        val mag = convertView.findViewById<View>(R.id.mag) as TextView
        val place = convertView.findViewById<View>(R.id.place) as TextView
        val date = convertView.findViewById<View>(R.id.date) as TextView
        val time = convertView.findViewById<View>(R.id.time) as TextView
        val near = convertView.findViewById<View>(R.id.near) as TextView

        //set the proper background color on the magnitude circle.
        val magnitudeCircle = mag.background as GradientDrawable


        //setting resources
        val getQuake = getItem(position)

        //get the appropriate background color based on the current earthquake magnitude
        magnitudeColor = getMagnitudeColor(getQuake!!.returnMag())

        //set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor)

        val location = getQuake.returnPlace()
        val split: Array<String>
        val offset: String
        val primary: String
        mag.text = getQuake.returnMag().toString()
        if (location.contains("of")) {
            split = location.split("of".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            offset = split[0]
            primary = split[1]
            near.text = offset + "of"
        } else {
            near.text = "Near the"
            primary = getQuake.returnPlace()
        }
        place.text = primary
        date.text = getQuake.returnDate()
        time.text = getQuake.returnTime()

        listMain.setOnClickListener {
            val url = getQuake.returnUrl()
            val openUrl = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(openUrl)
        }

        return convertView
    }

    private fun getMagnitudeColor(magnitude: Double): Int {
        when (magnitude.toInt()) {
            0 -> magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude1)
            1 -> magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude2)
            2 -> magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude3)
            3 -> magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude4)
            4 -> magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude5)
            5 -> magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude6)
            6 -> magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude7)
            7 -> magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude8)
            8 -> magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude9)
            9 -> magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude10plus)
            else -> magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude10plus)
        }
        return magnitudeColor
    }
}
