package com.mxcsyounes.alarmapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AlarmNotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(
            context,
            "The current Time is ${System.currentTimeMillis()}",
            Toast.LENGTH_SHORT
        ).show()
    }
}