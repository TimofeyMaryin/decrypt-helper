package custom.lib.droid.decrypt_helper

import android.util.Base64
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

    fun encrypt(input: String, secretKey: ByteArray = EncryptionHelper.secretKey): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        val iv = generateRandomIV()
        val keySpec = SecretKeySpec(secretKey, ALGORITHM)
        val ivParameterSpec = IvParameterSpec(iv)
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec)
        val encryptedBytes = cipher.doFinal(input.toByteArray())
        val combined = ByteArray(iv.size + encryptedBytes.size)
        System.arraycopy(iv, 0, combined, 0, iv.size)
        System.arraycopy(encryptedBytes, 0, combined, iv.size, encryptedBytes.size)
        return Base64.encodeToString(combined, Base64.DEFAULT)
    }

    fun decrypt(input: String, secretKey: ByteArray = EncryptionHelper.secretKey): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        val combined = Base64.decode(input, Base64.DEFAULT)
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







