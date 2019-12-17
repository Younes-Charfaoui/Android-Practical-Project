package com.mxcsyounes.broadcastreceivers

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        simpleButtonLaunch.setOnClickListener {
            sendOrderedBroadcast(
                Intent("com.mxcsyounes.broadcastreceivers.ACTION") , android.Manifest.permission.VIBRATE
            )
            //sendStickyBroadcast(Intent("com.mxcsyounes.broadcastreceivers.ACTION"))
        }
    }
}
