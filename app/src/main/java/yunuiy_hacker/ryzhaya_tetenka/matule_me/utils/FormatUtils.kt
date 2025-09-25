package yunuiy_hacker.ryzhaya_tetenka.matule_me.utils

import java.text.DecimalFormat

object FormatUtils {
    fun timeInSecondsToString(time: Int): String {
        val hours: Int = time / 60
        val minutes: Int = time % 60
        return DecimalFormat("00").format(hours) + ":" + DecimalFormat("00").format(minutes)
    }
}