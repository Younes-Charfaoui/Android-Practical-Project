package com.mxcsyounes.alarmapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val notificationIntent = Intent(this, AlarmNotificationReceiver::class.java)

        val notificationReceiverPendingIntent =
            PendingIntent.getBroadcast(this, 0, notificationIntent, 0)

        val loggerIntent = Intent(this, AlarmLoggerReceiver::class.java)

        val loggerReceiverPendingIntent = PendingIntent.getBroadcast(this, 0, loggerIntent, 0)

        singleAlarm.setOnClickListener {
            alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + 5000,
                notificationReceiverPendingIntent
            )

            alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + 5000 + 2000,
                loggerReceiverPendingIntent
            )

            Toast.makeText(this, "Single Alarm set", Toast.LENGTH_SHORT).show()

        }

        repeatingAlarm.setOnClickListener {
            alarmManager.setRepeating(
                AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + 5000,
                AlarmManager.INTERVAL_FIFTEEN_MINUTES,
                notificationReceiverPendingIntent
            )

            alarmManager.setRepeating(
                AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + 5000 + 2000,
               AlarmManager.INTERVAL_FIFTEEN_MINUTES,
                loggerReceiverPendingIntent
            )

            Toast.makeText(this, "Repeating Alarm set", Toast.LENGTH_SHORT).show()
        }

        inxactAlarm.setOnClickListener {
            alarmManager.setInexactRepeating(
                AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + 5000,
                AlarmManager.INTERVAL_FIFTEEN_MINUTES,
                notificationReceiverPendingIntent
            )

            alarmManager.setInexactRepeating(
                AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + 5000 + 2000,
                AlarmManager.INTERVAL_FIFTEEN_MINUTES,
                loggerReceiverPendingIntent
            )
            Toast.makeText(this, "Inexact Alarm set", Toast.LENGTH_SHORT).show()
        }

        cancelAlarm.setOnClickListener {
            alarmManager.cancel(
                notificationReceiverPendingIntent
            )

            alarmManager.cancel(
                loggerReceiverPendingIntent
            )

            Toast.makeText(this, "Canceling repeating Alarms", Toast.LENGTH_SHORT).show()
        }
    }
}
