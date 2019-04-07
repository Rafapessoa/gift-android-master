package ç

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.text.Html
import com.fiap.giftgift.NotificationManagerUtils
import com.fiap.giftgift.R

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        //remoteMessage.data
        showNotification(remoteMessage, getLastScreen())
    }

    private fun getLastScreen(): PendingIntent {
        val nIntent = packageManager.getLaunchIntentForPackage(packageName)
        return PendingIntent.getActivity(this, 0, nIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun showNotification(remoteMessage: RemoteMessage, pendingIntent: PendingIntent?) {
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val title = remoteMessage.notification?.title ?: "Nova mensagem"
        val message = remoteMessage.notification?.body ?: ""

        val mBuilder =
                createNotificationCompatBuilder(applicationContext)
                        .setContentTitle(Html.fromHtml(title))
                        .setSound(defaultSoundUri)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .setContentText(Html.fromHtml(message))

        val mNotificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(notificationID, mBuilder.build())
    }

    private fun createNotificationCompatBuilder(context: Context): NotificationCompat.Builder {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder(context, NotificationManagerUtils(context).getMainNotificationId())
        } else {
            NotificationCompat.Builder(context)
        }
    }

    companion object {
        private const val notificationID = 1000
    }
}
