package com.mxcsyounes.dynamicbroadcast

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = LocalBroadcastManager.getInstance(this)

        manager.registerReceiver(
            SimpleReceiver(),
            IntentFilter("com.mxcsyounes.dynamicbroadcast.ACTION")
        )

        launchButtn.setOnClickListener {
            manager.sendBroadcast(Intent("com.mxcsyounes.dynamicbroadcast.ACTION"))
        }
    }


}
