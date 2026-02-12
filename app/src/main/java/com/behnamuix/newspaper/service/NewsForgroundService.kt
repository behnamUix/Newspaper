package com.behnamuix.newspaper.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.behnamuix.newspaper.R
import com.behnamuix.newspaper.room.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class NewsForgroundService : Service() {

    companion object {
        const val ACTION_STOP = "STOP"
    }

    private val repo: NewsRepository by inject()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (intent?.action == ACTION_STOP) {
            stopForeground(true)
            stopSelf()
            return START_NOT_STICKY
        }

        startForegroundWithNotif()

        CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                try {
                    repo.refreshNews()
                    Log.d("service","news update!")

                } catch (e: Exception) {
                    e.printStackTrace()
                }
                delay(30_000)
            }
        }

        return START_STICKY
    }

    private fun startForegroundWithNotif() {
        val channelId = "foreground_channel"
        val manager = getSystemService(NotificationManager::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(
                NotificationChannel(
                    channelId,
                    "Foreground Service",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            )
        }

        val stopIntent = Intent(this, NewsForgroundService::class.java).apply {
            action = ACTION_STOP
        }

        val stopPendingIntent = PendingIntent.getService(
            this,
            0,
            stopIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.icon_live)
            .setContentTitle("News Sync is Active")
            .setContentText("Keeping your news up to date every 30 sec...")
            .setOngoing(true)
            .setColor(0xFF4CAF50.toInt())
            .setColorized(true)
            .addAction(R.drawable.icon_setting, "STOP SERVICE ⛔", stopPendingIntent)
            .build()

        startForeground(1, notification)
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
