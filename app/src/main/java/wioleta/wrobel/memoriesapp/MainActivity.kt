package wioleta.wrobel.memoriesapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import wioleta.wrobel.memoriesapp.ui.theme.MemoriesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemoriesAppTheme {
                Surface {
                    MainScreen()
                }
            }
        }
    }
    @Composable
    fun MainScreen() {
        val context: Context = LocalContext.current
            Column (horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
                ) {
                Text(
                    text = stringResource(id = R.string.add_first_memory),
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(12.dp))
                FloatingActionButton(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    onClick = {
                        val intent = Intent(context, AddMemoryActivity::class.java)
                        startActivity(intent)
                        //finish()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "add_icon",
                        tint = MaterialTheme.colorScheme.surface,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }

//    @Preview(showBackground = true)
//    @Composable
//    fun GreetingPreview() {
//        MemoriesAppTheme {
//            MainScreen()
//        }
//    }
}




