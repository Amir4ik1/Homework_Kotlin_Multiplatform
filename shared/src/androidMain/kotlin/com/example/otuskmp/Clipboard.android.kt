package com.example.otuskmp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

internal lateinit var appContext: Context

fun initClipboard(context: Context) {
    appContext = context.applicationContext
}

actual fun copyToClipboard(text: String) {
    val clipboard = appContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clipboard.setPrimaryClip(ClipData.newPlainText("stopwatch", text))
}
