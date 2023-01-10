package space.lobanovi.taskapp.services

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import space.lobanovi.taskapp.R

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class FirebaseNotifications : FirebaseMessagingService(){

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {

        sendNotification(message)
        super.onMessageReceived(message)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendNotification(remoteMessage: RemoteMessage){
        val notificationsBuilder = NotificationCompat.Builder(this,"task_channelId")
        notificationsBuilder.setSmallIcon(R.mipmap.ic_launcher_round)
        notificationsBuilder.setContentTitle(remoteMessage.notification?.title)
        notificationsBuilder.setContentTitle(remoteMessage.notification?.body)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager

       val channel = NotificationChannel(
           "task_channelId",
            "Chanel human readable title",
            NotificationManager.IMPORTANCE_DEFAULT)

        notificationManager.createNotificationChannel(channel)
        notificationManager.notify(1,notificationsBuilder.build())
    }
}
