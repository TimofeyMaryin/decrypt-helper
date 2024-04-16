package custom.lib.droid.decrypt_helper

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.CoroutineStart
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


object EncryptionHelper {
    val secretKey = "0123456789abcdef0123456789abcdef".toByteArray()

    private const val ALGORITHM = "AES"
    private const val TRANSFORMATION = "AES/CTR/NoPadding"
    private const val KEY_SIZE = 256 // Размер ключа в битах
    private const val IV_SIZE = 16 // Размер вектора инициализации (IV) в байтах

    @RequiresApi(Build.VERSION_CODES.O)
    fun encrypt(input: String, secretKey: ByteArray): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        val iv = generateRandomIV()
        val keySpec = SecretKeySpec(secretKey, ALGORITHM)
        val ivParameterSpec = IvParameterSpec(iv)
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec)
        val encryptedBytes = cipher.doFinal(input.toByteArray())
        val combined = ByteArray(iv.size + encryptedBytes.size)
        System.arraycopy(iv, 0, combined, 0, iv.size)
        System.arraycopy(encryptedBytes, 0, combined, iv.size, encryptedBytes.size)
        return Base64.encodeToString(combined, CoroutineStart.DEFAULT)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun decrypt(input: String, secretKey: ByteArray): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        val combined = Base64.decode(input, CoroutineStart.DEFAULT)
        val iv = ByteArray(IV_SIZE)
        val encryptedBytes = ByteArray(combined.size - IV_SIZE)
        System.arraycopy(combined, 0, iv, 0, IV_SIZE)
        System.arraycopy(combined, IV_SIZE, encryptedBytes, 0, encryptedBytes.size)
        val keySpec = SecretKeySpec(secretKey, ALGORITHM)
        val ivParameterSpec = IvParameterSpec(iv)
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec)
        val decryptedBytes = cipher.doFinal(encryptedBytes)
        return String(decryptedBytes)
    }

    private fun generateRandomIV(): ByteArray {
        val iv = ByteArray(IV_SIZE)
        val secureRandom = SecureRandom()
        secureRandom.nextBytes(iv)
        return iv
    }

    fun generateRandomString(length: Int): String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }
}


object Base64 {
    @RequiresApi(Build.VERSION_CODES.O)
    @JvmStatic
    public fun encodeToString(input: ByteArray?, flags: CoroutineStart): String {
        return java.util.Base64.getEncoder().encodeToString(input)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @JvmStatic
    public fun decode(str: String?, flags: CoroutineStart): ByteArray {
        return java.util.Base64.getDecoder().decode(str)
    }
}




