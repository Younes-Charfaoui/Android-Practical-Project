package com.mxcsyounes.earthquaketracker.adapters

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mxcsyounes.earthquaketracker.R
import com.mxcsyounes.earthquaketracker.api.models.geonames.Earthquake
import kotlinx.android.synthetic.main.earthquake_item.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.floor

class EarthquakeAdapter : RecyclerView.Adapter<EarthquakeAdapter.EarthquakeViewHolder>() {

    private var data: List<Earthquake> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarthquakeViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.earthquake_item, parent, false)
        return EarthquakeViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: EarthquakeViewHolder, position: Int) {
        with(data[position]) {
            holder.itemView.magnitudeTextView.text = magnitude.toString()

            val depthText = "Depth of $depth km"
            holder.itemView.depthTextView.text = depthText


            holder.itemView.magnitudeTextView.text = magnitude.toString()

            val latLngText = "$lat , $lng"
            holder.itemView.latLngTextView.text = latLngText

            holder.itemView.magnitudeTextView.text = magnitude.toString()
            val magnitudeCircle = holder.itemView.magnitudeTextView.background as GradientDrawable
            magnitudeCircle.setColor(getMagnitudeColor(holder.itemView.context, magnitude))

            val date = createDate(datetime)
            holder.itemView.dateTextView.text = formatDate(date)
            holder.itemView.timeTextView.text = formatTime(date)
        }
    }


    inner class EarthquakeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private fun createDate(date: String): Date {
        return SimpleDateFormat("yyyy-MM-DD hh:mm:ss", Locale.getDefault()).parse(date)!!
    }

    private fun formatTime(date: Date): String {
        val dateFormat = SimpleDateFormat("h: mm a", Locale.getDefault())
        return dateFormat.format(date)
    }

    private fun formatDate(date: Date): String {
        val dateFormat = SimpleDateFormat("MM EEE, yyyy", Locale.getDefault())
        return dateFormat.format(date)
    }

    private fun getMagnitudeColor(context: Context, magnitude: Double): Int {

        val magnitudeColorResourceId: Int = when (floor(magnitude).toInt()) {
            0, 1 -> R.color.magnitude1
            2 -> R.color.magnitude2
            3 -> R.color.magnitude3
            4 -> R.color.magnitude4
            5 -> R.color.magnitude5
            6 -> R.color.magnitude6
            7 -> R.color.magnitude7
            8 -> R.color.magnitude8
            9 -> R.color.magnitude9
            else -> R.color.magnitude10plus
        }
        return ContextCompat.getColor(context, magnitudeColorResourceId)
    }

    fun changeData(it: List<Earthquake>) {
        data = it
        notifyDataSetChanged()
    }
}