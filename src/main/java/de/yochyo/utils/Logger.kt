package de.yochyo.utils

import java.io.File
import java.io.PrintWriter
import java.io.StringWriter

class Logger(dir: String) {
    private val directory = File("$dir/logs/")

    init {
        directory.mkdirs()
    }

    fun log(message: String, filePrefix: String = "logcat") {
        val logFile = File(directory, "$filePrefix ${System.currentTimeMillis()}.txt")
        logFile.createNewFile()
        logFile.writeText(message)
    }

    fun log(e: Throwable, info: String = "", filePrefix: String = "logcat") {
        val errors = StringWriter()
        e.printStackTrace(PrintWriter(errors))
        log("$info\n$errors", filePrefix)
    }
}