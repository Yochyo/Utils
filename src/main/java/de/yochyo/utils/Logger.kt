package de.yochyo.utils

import java.io.File
import java.io.PrintWriter
import java.io.StringWriter
import kotlin.system.exitProcess

class Logger(dir: String) {
    private val directory = File("$dir/logs/")

    init {
        directory.mkdirs()
    }

    fun log(message: String) {
        val logFile = File(directory, "logcat ${System.currentTimeMillis()}.txt")
        logFile.createNewFile()
        logFile.writeText(message)
    }

    fun log(e: Throwable, info: String = "") {
        val errors = StringWriter()
        e.printStackTrace(PrintWriter(errors))
        log("$info\n$errors")
    }
}