package com.mxcsyounes.fragmentapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*


class IDEListActivity : AppCompatActivity() {

    private var isTablet: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        if (ide_detail_container != null) isTablet = true

        ideRecylcerView.adapter = IDEAdapter(this, IDEContent.ideList, isTablet)
    }
}
