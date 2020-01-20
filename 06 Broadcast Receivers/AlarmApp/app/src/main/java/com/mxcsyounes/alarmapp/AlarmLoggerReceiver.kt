package com.mxcsyounes.alarmapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AlarmLoggerReceiver : BroadcastReceiver() {

    var file = true

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("TAGME", "The current Time is ${System.currentTimeMillis()}")
    }
}