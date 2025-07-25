package ua.sviatkuzbyt.catfact.other

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

interface NetworkMonitor {
    fun isConnected(): Boolean
}

class NetworkMonitorImpl(
    private val context: Context
) : NetworkMonitor {
    override fun isConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = cm.activeNetwork ?: return false
        val capabilities = cm.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }
}