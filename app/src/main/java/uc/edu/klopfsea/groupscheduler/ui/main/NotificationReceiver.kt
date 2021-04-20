package uc.edu.klopfsea.groupscheduler.ui.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import uc.edu.klopfsea.groupscheduler.R

class NotificationReceiver : BroadcastReceiver() {

    private var context: Context? = null
    private lateinit var channel: NotificationChannel
    private var isChannelCreated = false
    private val EVENT_CHANNEL_ID = "EVENT_CHANNEL_ID"

    override fun onReceive(context: Context?, intent: Intent?) {
        this.context = context
        val action = intent?.action
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        var networkType = ""

        capabilities ?: return

        when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                networkType = "Wifi"
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                networkType = "Cellular"
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                networkType = "Ethernet"
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> {
                networkType = "Bluetooth"
            }
        }

        when (action) {
            Intent.ACTION_POWER_CONNECTED -> {
                notifyUser()
            }
            Intent.ACTION_POWER_DISCONNECTED -> {

            }
        }
    }

    private fun notifyUser() {
        if (!isChannelCreated) {
            createChannel()
        }
        val mBuilder = NotificationCompat.Builder(context!!, EVENT_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Check your schedule for today!")
                .setContentText("Arrange your events accordingly!")
        val notification = mBuilder.build()
        val notificationManagerCompat = NotificationManagerCompat.from(context!!)
        notificationManagerCompat.notify(1, notification)
    }

    private fun createChannel() {
        channel = NotificationChannel(EVENT_CHANNEL_ID, "Events", NotificationManager.IMPORTANCE_DEFAULT)
        channel.description = "A channel for events"
        channel.lightColor = Color.GREEN
        val notificationManager = ContextCompat.getSystemService(context!!, NotificationManager::class.java)
        notificationManager!!.createNotificationChannel(channel)
        isChannelCreated = true
    }
}