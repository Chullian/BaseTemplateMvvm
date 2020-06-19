package com.chullian.template.tools.extensions


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import kotlin.math.round

fun Context.hasNetwork(): Boolean {
    var result = false
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        cm?.run {
            cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        }
    } else {
        cm?.run {
            cm.activeNetworkInfo?.run {
                if (type == ConnectivityManager.TYPE_WIFI) {
                    result = true
                } else if (type == ConnectivityManager.TYPE_MOBILE) {
                    result = true
                }
            }
        }
    }
    return result
}

fun String?.toFloatEx(): Float {
    return if (this.isNullOrEmpty()) 0f
    else this.toFloat()
}

fun Float?.toFloatEx(): Float {
    return this?.toFloat() ?: 0f
}

fun Int?.toFloatEx(): Float {
    return this?.toFloat() ?: 0f
}

fun String?.toIntEx(): Int {
    return if (this.isNullOrEmpty()) 0
    else this.toInt()
}

fun Float?.toIntEx(): Int {
    return this?.toInt() ?: 0
}

fun Int?.toIntEx(): Int {
    return this?.toInt() ?: 0
}

fun String?.toStringEx(): String {
    return when {
        this.isNullOrEmpty() -> ""
        this == "null" -> ""
        else -> this.toString()
    }
}

fun Float?.toStringEx(): String {
    return when {
        this == null -> "0"
        else -> this.toString()
    }
}

fun Int?.toStringEx(): String {
    return when {
        this == null -> "0"
        else -> this.toString()
    }
}

fun Float.round(num: Int): Float {
    val valueToConvert = this.toDouble()
    var multiplier = 1.0
    repeat(num) { multiplier *= 10 }
    var value = round(valueToConvert * multiplier) / multiplier
    return value.toFloat()
}
