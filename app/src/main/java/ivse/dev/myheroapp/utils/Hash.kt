package ivse.dev.myheroapp.utils

import java.math.BigInteger
import java.security.MessageDigest

object Hash {
    fun createHash(input: String): String {
        val md5 = MessageDigest.getInstance("MD5")
        return BigInteger(1, md5.digest(input.toByteArray()))
            .toString(16).padStart(32, '0')
    }
}