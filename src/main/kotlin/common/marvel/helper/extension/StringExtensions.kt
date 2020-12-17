package common.marvel.helper.extension

/**
 * @author leo
 */
/**
 * slf4j string placeholder style to replace '{}' in string from args
 */
fun String.replaceByArgs(vararg args: Any) = args
    .fold(this, { s, arg -> s.replaceFirst("\\{}".toRegex(), arg.toString()) })

private val regexURL = "(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]".toRegex()
/**
 * regex the first URL
 */
fun String.regexURL(): String? = regexURL.find(this).let { it?.value }

/**
 * xor with hex string
 */
fun String.xor(hexString: String): String = this
    .mapIndexed { index, char ->
        val source = Character.digit(char, 16)
        val target = Character.digit(hexString.toCharArray()[index], 16)
        return@mapIndexed source.xor(target).toString(16)
    }
    .joinToString("")
