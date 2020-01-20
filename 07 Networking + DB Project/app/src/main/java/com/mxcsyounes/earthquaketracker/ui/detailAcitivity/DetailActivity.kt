package com.mxcsyounes.earthquaketracker.ui.detailAcitivity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mxcsyounes.earthquaketracker.R
import com.mxcsyounes.earthquaketracker.adapters.EarthquakeAdapter
import com.mxcsyounes.earthquaketracker.api.models.geonames.Earthquake
import jp.wasabeef.recyclerview.animators.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var earthquakeAdapter: EarthquakeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)

        recyclerViewSetup()

        detailViewModel.getAllData().observe(this, Observer<List<Earthquake>> {
            detailProgressBar.visibility = View.GONE
            if (it.isEmpty()) {
                emptyView.visibility = View.VISIBLE
                detailRecyclerView.visibility = View.GONE
            } else {
                detailRecyclerView.visibility = View.VISIBLE
                emptyView.visibility = View.GONE
                earthquakeAdapter.changeData(it)
            }
        })
    }

    private fun recyclerViewSetup() {
        earthquakeAdapter = EarthquakeAdapter()
        val alphaAdapter = AlphaInAnimationAdapter(earthquakeAdapter)
        detailRecyclerView.adapter = ScaleInAnimationAdapter(alphaAdapter)
        detailRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete_all -> {
                detailViewModel.deleteAll()
                true
            }
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
