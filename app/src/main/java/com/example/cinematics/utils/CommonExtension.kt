package com.example.cinematics.utils

import okhttp3.ResponseBody
import org.json.JSONObject
import kotlin.time.Duration.Companion.minutes

fun ResponseBody.jsonData(): JSONObject {
    return JSONObject(this.string())
}

fun Int.hourMinutes(): String {
    return "${this.minutes.inWholeHours}h ${this % 60}m"
}

fun Int.genderInString(): String {
    return when (this) {
        1 -> "Female"
        2 -> "Male"
        else -> ""
    }
}