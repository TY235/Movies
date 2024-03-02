package com.tzeyi.movies.utilities

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun isInternetAvailable(application: Application): Boolean {
    val connectivityManager =
        application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val nw = connectivityManager.activeNetwork ?: return false
    return connectivityManager.getNetworkCapabilities(nw)
        ?.let { activeNw ->
            listOf(
                activeNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI),
                activeNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR),
                activeNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET),
            ).any { it }
        } ?: false
}
