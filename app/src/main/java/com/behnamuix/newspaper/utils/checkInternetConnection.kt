package com.behnamuix.newspaper.utils

import android.content.Context
import android.net.ConnectivityManager

fun checkNet(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = cm.activeNetworkInfo
    return network != null && network.isConnected
}