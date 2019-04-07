package com.fiap.giftgift

import android.app.NotificationChannel
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi

class NotificationManagerUtils(private val context: Context) {

    companion object {
        private val CHANNEL_ID = "YOUR_CHANNEL_ID"
        private val CHANNEL_NAME = "Your human readable notification channel name"
        private val CHANNEL_DESCRIPTION = "description"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMainNotificationId(): String {
        return CHANNEL_ID
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createMainNotificationChannel() {
        val id = CHANNEL_ID
        val name = CHANNEL_NAME
        val description = CHANNEL_DESCRIPTION
        val importance = android.app.NotificationManager.IMPORTANCE_LOW
        val mChannel = NotificationChannel(id, name, importance)
        mChannel.description = description
        mChannel.enableLights(true)
        mChannel.lightColor = Color.RED
        mChannel.enableVibration(true)
        val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager
        mNotificationManager.createNotificationChannel(mChannel)
    }
}
