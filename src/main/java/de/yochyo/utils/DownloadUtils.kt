package de.yochyo.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

object DownloadUtils {
    suspend fun getUrlLines(urlToRead: String): StringBuilder {
        return withContext(Dispatchers.IO) {
            val builder = StringBuilder()
            try {
                val stream = getUrlInputStream(urlToRead)
                if (stream != null) {
                    BufferedReader(InputStreamReader(stream, "UTF-8")).use { bufferedReader ->
                        var inputLine: String? = bufferedReader.readLine()
                        while (inputLine != null) {
                            builder.append(inputLine)
                            inputLine = bufferedReader.readLine()
                        }
                        stream.close()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            builder
        }
    }

    suspend fun getJson(urlToRead: String): JSONArray? {
        var array: JSONArray? = null
        try {
            array = JSONArray(getUrlLines(urlToRead).toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return array
    }

    suspend fun getUrlInputStream(url: String): InputStream? {
        return withContext(Dispatchers.IO) {
            return@withContext try {
                val conn = URL(url).openConnection() as HttpURLConnection
                conn.addRequestProperty("User-Agent", "Mozilla/5.00")
                conn.requestMethod = "GET"
                conn.inputStream
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}