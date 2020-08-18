package de.yochyo.utils

import java.io.File
import java.io.PrintWriter
import java.io.StringWriter

open class Logger(dir: String) {
    protected val directory = File("$dir/logs/")

    init {
        directory.mkdirs()
    }

    open fun log(message: String, filePrefix: String = "logcat") {
        val logFile = File(directory, "$filePrefix ${System.currentTimeMillis()}.txt")
        logFile.createNewFile()
        logFile.writeText(message)
    }

    open fun log(e: Throwable, info: String = "", filePrefix: String = "logcat") {
        val errors = StringWriter()
        e.printStackTrace(PrintWriter(errors))
        log("$info\n$errors", filePrefix)
    }
}