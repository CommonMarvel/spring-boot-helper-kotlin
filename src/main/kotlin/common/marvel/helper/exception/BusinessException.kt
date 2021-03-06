package common.marvel.helper.exception

import common.marvel.helper.extension.replaceByArgs

/**
 * @author leo
 */
class BusinessException : RuntimeException {

    val errorCode: ErrorCode

    constructor(code: ErrorCode) : super(code.message) {
        this.errorCode = code
    }

    constructor(code: ErrorCode, cause: Throwable) : super(code.message, cause) {
        this.errorCode = code
    }

    constructor(code: ErrorCode, message: String, vararg args: Any) : super(message.replaceByArgs(*args)) {
        this.errorCode = code
    }

    constructor(
        code: ErrorCode,
        cause: Throwable,
        message: String,
        vararg args: Any
    ) : super(message.replaceByArgs(*args), cause) {
        this.errorCode = code
    }
}
