package custom.lib.droid.decryptalgorytm

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import custom.lib.droid.decrypt_helper.EncryptionHelper
import custom.lib.droid.decryptalgorytm.ui.theme.DecryptAlgorytmTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val origText = "Hello World"
        val encryptText = EncryptionHelper.encrypt(origText, EncryptionHelper.secretKey)
        val decryptText = EncryptionHelper.decrypt(encryptText, EncryptionHelper.secretKey)
        Log.e("TAG", "origText: $origText", )
        Log.e("TAG", "encryptText: $encryptText", )
        Log.e("TAG", "decryptText: $decryptText", )

        setContent {
            DecryptAlgorytmTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DecryptAlgorytmTheme {
        Greeting("Android")
    }
}