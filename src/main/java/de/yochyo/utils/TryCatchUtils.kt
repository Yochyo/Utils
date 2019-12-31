package de.yochyo.utils


fun <E> tryCatch(method: () -> E): TryCatchResult<E> {
    val result: TryCatchResult<E>
    result = try {
        val r = method()
        TryCatchResult(r, null)
    } catch (e: Exception) {
        TryCatchResult(null, e)
    }
    return result
}

suspend fun <E> tryCatchSuspended(method: suspend () -> E): TryCatchResult<E> {
    val result: TryCatchResult<E>
    result = try {
        val r = method()
        TryCatchResult(r, null)
    } catch (e: Exception) {
        TryCatchResult(null, e)
    }
    return result
}

class TryCatchResult<E>(val value: E?, internal val exception: Exception?) {
    fun catch(method: (e: Exception) -> E): TryCatchResult<E> {
        if (exception != null) method(exception)
        return this
    }

    fun finally(method: () -> E): TryCatchResult<E> {
        method()
        return this
    }

    fun printStackTrace(): TryCatchResult<E> {
        exception?.printStackTrace()
        return this
    }

    fun log(logger: Logger, message: String = ""): TryCatchResult<E> {
        if (exception != null) {
            logger.log(exception)
        }
        return this
    }

    fun logAndPrintStackTrace(logger: Logger, message: String = ""): TryCatchResult<E> {
        if (exception != null) {
            exception.printStackTrace()
            logger.log(exception)
        }
        return this
    }
}