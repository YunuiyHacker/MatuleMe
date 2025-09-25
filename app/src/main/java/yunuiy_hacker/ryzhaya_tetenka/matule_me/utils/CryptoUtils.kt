package yunuiy_hacker.ryzhaya_tetenka.matule_me.utils

import java.security.MessageDigest

object CryptoUtils {
    fun toSHA256(string: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(string.toByteArray(Charsets.UTF_8))

        return hashBytes.fold("") { string, byte -> string + "%02x".format(byte) }
    }
}