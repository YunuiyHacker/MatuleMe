package yunuiy_hacker.ryzhaya_tetenka.matule_me.utils

import java.util.regex.Pattern

object RegexPatterns {
    val PATTERN_EMAIL =
        Pattern.compile(
            "[a-z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-z0-9][a-z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-z0-9][a-z0-9\\-]{0,25}" +
                    ")+"
        )
}