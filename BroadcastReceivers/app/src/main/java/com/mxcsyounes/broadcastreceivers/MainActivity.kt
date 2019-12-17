package com.mxcsyounes.broadcastreceivers

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        simpleButtonLaunch.setOnClickListener {
            sendBroadcast(Intent("com.mxcsyounes.broadcastreceivers.ACTION"))
            //sendStickyBroadcast(Intent("com.mxcsyounes.broadcastreceivers.ACTION"))
        }
    }
}
