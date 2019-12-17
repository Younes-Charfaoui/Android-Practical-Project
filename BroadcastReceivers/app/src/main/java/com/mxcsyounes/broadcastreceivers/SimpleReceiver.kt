package com.mxcsyounes.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Vibrator
import android.util.Log
import android.widget.Toast


class SimpleReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("MeTag", "### INTENT Received ###")
        val vibrator: Vibrator? = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?

        vibrator?.vibrate(1500)

        Toast.makeText(context, "### INTENT Received ###", Toast.LENGTH_SHORT).show()
    }
}