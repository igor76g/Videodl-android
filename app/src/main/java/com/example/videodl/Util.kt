package com.example.videodl

import java.util.Locale

private val ANSI = Regex("\u001B\\[[0-9;]*m")

/** Убирает цветовые коды и префикс ERROR: из сообщения yt-dlp. */
fun cleanMsg(msg: String?): String =
    ANSI.replace(msg ?: "", "").trim().removePrefix("ERROR:").trim().take(300)

fun human(bytes: Long): String {
    var n = bytes.toDouble()
    val units = arrayOf("Б", "КБ", "МБ", "ГБ")
    for (u in units) {
        if (n < 1024) {
            return if (u == "Б") "${n.toInt()} $u" else String.format(Locale.getDefault(), "%.1f %s", n, u)
        }
        n /= 1024
    }
    return String.format(Locale.getDefault(), "%.1f ТБ", n)
}

fun fmtTime(sec: Long): String {
    val h = sec / 3600
    val m = (sec % 3600) / 60
    val s = sec % 60
    return if (h > 0) String.format(Locale.US, "%d:%02d:%02d", h, m, s)
    else String.format(Locale.US, "%d:%02d", m, s)
}
