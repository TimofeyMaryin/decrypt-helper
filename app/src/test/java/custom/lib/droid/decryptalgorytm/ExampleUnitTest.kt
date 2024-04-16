package custom.lib.droid.decryptalgorytm

import custom.lib.droid.decrypt_helper.EncryptionHelper
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {

    @Test
    fun linkIsCorrectAfterEncrypt_1() {
        val orig = "http://investment-easy.ru/"

        val encryptText = EncryptionHelper.encrypt(orig, EncryptionHelper.secretKey)
        val decryptText = EncryptionHelper.decrypt(encryptText, EncryptionHelper.secretKey)

        assertEquals(orig, decryptText)
    }

    @Test
    fun linkIsCorrectAfterEncrypt_2() {
        val orig = "https://investment-easy.ru/6Scxm2"

        val encryptText = EncryptionHelper.encrypt(orig, EncryptionHelper.secretKey)
        val decryptText = EncryptionHelper.decrypt(encryptText, EncryptionHelper.secretKey)

        assertEquals(orig, decryptText)
    }

    @Test
    fun linkIsCorrectAfterEncrypt_3() {
        val orig = "http://trading-finance.ru/"

        val encryptText = EncryptionHelper.encrypt(orig, EncryptionHelper.secretKey)
        val decryptText = EncryptionHelper.decrypt(encryptText, EncryptionHelper.secretKey)

        assertEquals(orig, decryptText)
    }

    @Test
    fun linkIsCorrectAfterEncrypt_4() {
        val orig = "http://investment-longterm.ru/"

        val encryptText = EncryptionHelper.encrypt(orig, EncryptionHelper.secretKey)
        val decryptText = EncryptionHelper.decrypt(encryptText, EncryptionHelper.secretKey)

        assertEquals(orig, decryptText)
    }

    @Test
    fun linkIsCorrectAfterEncrypt_5() {
        val orig = "https://attractive-investment5.ru/"

        val encryptText = EncryptionHelper.encrypt(orig, EncryptionHelper.secretKey)
        val decryptText = EncryptionHelper.decrypt(encryptText, EncryptionHelper.secretKey)

        assertEquals(orig, decryptText)
    }

    @Test
    fun linkIsCorrectAfterEncrypt_6() {
        val orig = "http://attractive-investment4.ru/"

        val encryptText = EncryptionHelper.encrypt(orig, EncryptionHelper.secretKey)
        val decryptText = EncryptionHelper.decrypt(encryptText, EncryptionHelper.secretKey)

        assertEquals(orig, decryptText)
    }
}