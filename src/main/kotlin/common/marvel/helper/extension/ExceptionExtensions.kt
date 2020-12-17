package common.marvel.helper.extension

import common.marvel.helper.exception.BusinessException
import common.marvel.helper.exception.ErrorCode
import org.slf4j.Logger

/**
 * @author leo
 */
fun <T> T.errWhen(errorCode: ErrorCode, block: (t: T) -> Boolean): T {
    if (block.invoke(this)) {
        throw BusinessException(errorCode)
    }
    return this
}

fun <T> T?.errWhenNull(errorCode: ErrorCode): T {
    return this ?: throw BusinessException(errorCode)
}

fun <T, R> T.errWhenException(errorCode: ErrorCode, block: (t: T) -> R): R = try {
    block.invoke(this)
} catch (t: Throwable) {
    throw BusinessException(errorCode, t)
}

fun <T, R> T.errLogWhenException(errorCode: ErrorCode, log: Logger, block: (t: T) -> R): R = try {
    block.invoke(this)
} catch (t: Throwable) {
    log.error(t.message, t)
    throw BusinessException(errorCode, t)
}
