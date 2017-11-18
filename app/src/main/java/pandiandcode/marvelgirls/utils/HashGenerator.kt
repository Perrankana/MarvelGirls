package pandiandcode.marvelgirls.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * Created by Rocio Ortega on 18/11/2017.
 */


fun generateMd5(timestamp: Long, privateKey: String, publicKey: String): String? {
    return try {
        val concatResult = timestamp.toString() + privateKey + publicKey
        md5(concatResult)
    } catch (e: Exception) {
        null
    }

}

@Throws(NoSuchAlgorithmException::class)
fun md5(s: String): String {
    // Create MD5 Hash
    val digest = MessageDigest.getInstance("MD5")
    digest.update(s.toByteArray())
    val messageDigest = digest.digest()
    val bigInt = BigInteger(1, messageDigest)
    var hashText = bigInt.toString(16)
    // Now we need to zero pad it if you actually want the full 32
    // chars.
    while (hashText.length < 32) {
        hashText = "0" + hashText
    }
    return hashText
}


