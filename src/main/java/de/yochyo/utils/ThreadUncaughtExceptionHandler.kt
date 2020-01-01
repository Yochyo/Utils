package de.yochyo.utils

import kotlin.system.exitProcess

object ThreadUncaughtExceptionHandler {
    fun init(logger: Logger){
        Thread.setDefaultUncaughtExceptionHandler(ThreadExceptionHandler(logger))
    }
}
class ThreadExceptionHandler(val logger: Logger) : Thread.UncaughtExceptionHandler {
    override fun uncaughtException(t: Thread?, e: Throwable?) {
        if (e != null)
            logger.log(e)
        exitProcess(10)
    }
}