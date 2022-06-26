package com.example.notification2

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class MainActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    val channelId = "i.apps.notifications"
    val description = "this is notification app"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.click_button)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        button.setOnClickListener {
            val intent = Intent(this, afterNotifications::class.java)
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            val contentView = RemoteViews(packageName, R.layout.activity_after_notifications)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_DEFAULT)
                notificationChannel.enableVibration(true)
                notificationManager.createNotificationChannel(notificationChannel)
                builder = Notification.Builder(this, channelId)
                    .setContentIntent(pendingIntent)
                    .setContentTitle("Hello Sir")
                    .setContentText("Always for you sir✨✨🎊")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setAutoCancel(true)
            }

            notificationManager.notify(1234, builder.build())


        }
    }
}