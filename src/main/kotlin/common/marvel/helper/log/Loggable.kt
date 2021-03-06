package common.marvel.helper.log

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * @author leo
 */
interface Loggable {
    val logger: Logger
        get() = LoggerFactory.getLogger(javaClass)
}
