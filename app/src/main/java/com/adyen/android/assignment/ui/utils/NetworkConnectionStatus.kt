package com.adyen.android.assignment.ui.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun Context.hasNetworkConnection(): Boolean {
    with(getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager) {
        getNetworkCapabilities(activeNetwork)?.let { capabilities ->
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } ?: return false
    }
}