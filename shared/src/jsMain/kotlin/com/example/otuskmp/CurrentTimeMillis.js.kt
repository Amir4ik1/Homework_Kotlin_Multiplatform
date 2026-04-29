package com.example.otuskmp

import kotlin.js.Date

actual fun currentTimeMillis(): Long = Date().getTime().toLong()
