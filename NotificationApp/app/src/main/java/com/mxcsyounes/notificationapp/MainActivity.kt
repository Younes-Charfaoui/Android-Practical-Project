package com.mxcsyounes.notificationapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var counterSimple = 0
    var counterCustom = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "MyChannel"
            val descriptionText = "description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("MyChannel", name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        customNotificationButton.setOnClickListener {
            createNotification(114, true)
        }

        simpleNotificationButton.setOnClickListener {
            createNotification(112)
        }
    }

    private fun createNotification(id: Int, custom: Boolean = false) {
        val intent = Intent(this, NotificationActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val builder = NotificationCompat.Builder(this, "MyChannel")
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        Log.d("TagSound" , "hi ${alarmSound.toString()}")

        if (custom) {
            counterCustom++
            val expandedView = RemoteViews(packageName, R.layout.custom_notification)
            val collapsedView = RemoteViews(packageName, R.layout.collapsed_custom_not)


            val leftIntent = Intent(this, NotificationActivity::class.java)
            leftIntent.action = "left"
            expandedView.setOnClickPendingIntent(
                R.id.right_button,
                pendingIntent
            )

            builder.setSmallIcon(R.drawable.ic_android_black_24dp)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setCustomContentView(collapsedView)
                .setSound(alarmSound)
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
                .setCustomBigContentView(expandedView)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        } else {
            counterSimple++
            builder.setContentTitle("Simple Notification ($counterSimple)")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setAutoCancel(true)
                .setSound(alarmSound)
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
                .setContentText("Hi there, this is a simple notification")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        }


        with(NotificationManagerCompat.from(this)) {
            notify(id, builder.build())
        }
    }
}
