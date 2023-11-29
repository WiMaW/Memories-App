package wioleta.wrobel.memoriesapp

import android.content.Intent
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.material.datepicker.MaterialDatePicker
import wioleta.wrobel.memoriesapp.model.MemoryCardColors
import wioleta.wrobel.memoriesapp.model.MemoryFontColor
import wioleta.wrobel.memoriesapp.model.MemoryFontFamily
import wioleta.wrobel.memoriesapp.ui.theme.MemoriesAppTheme
import wioleta.wrobel.memoriesapp.ui.theme.font_oleo_regular
import java.time.format.TextStyle

class AddMemoryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemoriesAppTheme {
                Surface {
                    AddMemoryScreen()
                }
            }
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AddMemoryScreen() {
        val context = LocalContext.current

        var memoryDate by remember  {mutableStateOf("")}
        var memoryTitle by remember { mutableStateOf("") }
        var memoryDescription by remember { mutableStateOf("") }
        var memoryImage: Image

        val cardColors = MemoryCardColors.values()
        val fontColors = MemoryFontColor.values()
        val fontsFamily = MemoryFontFamily.values()


        var currentCardColor by remember { mutableStateOf(cardColors.first()) }
        var currentFontColor by remember { mutableStateOf(fontColors.first()) }
        var currentFontFamily by remember { mutableStateOf(fontsFamily.first()) }

        val textStyle: androidx.compose.ui.text.TextStyle = androidx.compose.ui.text.TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            fontFamily = currentFontFamily.font,
            color = currentFontColor.color
        )

        val scrollState = rememberScrollState()

        Card (
            colors = CardDefaults.cardColors(currentCardColor.color),
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
                .verticalScroll(scrollState)
        ){
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 26.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.add_memory),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                //Date type how to do it??
                OutlinedTextField(
                    value = memoryDate,
                    onValueChange = {memoryDate = it},
                    label = { Text(
                        text = stringResource(id = R.string.memory_date),
                        style = MaterialTheme.typography.labelMedium,
                    )},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = MaterialTheme.colorScheme.primary,textColor = Color.Gray),
                    textStyle = textStyle,
                    modifier = Modifier
                        .height(66.dp)
                        .fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    value = memoryTitle,
                    onValueChange = {memoryTitle = it},
                    label = {
                        Text(
                            text = stringResource(id = R.string.memory_title),
                            style = MaterialTheme.typography.labelMedium,
                        )},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = MaterialTheme.colorScheme.primary, textColor = Color.Gray),
                    textStyle = textStyle,
                    modifier = Modifier
                        .height(66.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    value = memoryDescription,
                    onValueChange = {memoryDescription = it},
                    label = {
                        Text(
                            text = stringResource(id = R.string.memory_description),
                            style = MaterialTheme.typography.labelMedium,
                        )},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = MaterialTheme.colorScheme.primary,textColor = Color.Gray),
                    textStyle = textStyle,
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(5.dp))
                //how to add image??
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = {
                        Text(
                            text = stringResource(id = R.string.add_image),
                            style = MaterialTheme.typography.labelMedium,
                        )},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                        textColor = Color.Gray),
                    textStyle = textStyle,
                    modifier = Modifier
                        .height(66.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                    items(items = cardColors) {cardColor ->
                        ElevatedButton(
                            onClick = { currentCardColor = cardColor},
                            colors = ButtonDefaults.buttonColors(containerColor = cardColor.color),
                            shape = MaterialTheme.shapes.large,
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                            modifier = Modifier.size(49.dp),
                        ) {}
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(items = fontColors) {fontColor ->
                        ElevatedButton(
                            onClick = { currentFontColor = fontColor},
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
                            shape = MaterialTheme.shapes.large,
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                            modifier = Modifier.height(40.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.font_color_text),
                                color = fontColor.color,
                                style = MaterialTheme.typography.labelSmall,
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(9.dp)) {
                    items(items = fontsFamily) {fontFamily ->
                        ElevatedButton(
                            onClick = { currentFontFamily = fontFamily},
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
                            shape = MaterialTheme.shapes.large,
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                            modifier = Modifier.height(40.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.font_color_text),
                                style = MaterialTheme.typography.labelSmall,
                                color = currentFontColor.color,
                                fontFamily = fontFamily.font,
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row (){
                    ElevatedButton(
                        onClick = {
                            val intent = Intent(context, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        },
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                        shape = MaterialTheme.shapes.large,
                        modifier = Modifier.width(110.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.cancel_button),
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    ElevatedButton(
                        onClick = {
                            val intent = Intent(context, MainActivity::class.java)
                            startActivity(intent) },
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                        shape = MaterialTheme.shapes.large,
                        modifier = Modifier.width(110.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.save_button),
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview2() {
        MemoriesAppTheme {
            AddMemoryScreen()
        }
    }
}



@Composable
fun MemoryCardColorButton() {

    val cardColors = MemoryCardColors.values()

    LazyRow(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
        items(items = cardColors) {cardColor ->
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = cardColor.color),
                shape = MaterialTheme.shapes.large,
                modifier = Modifier.size(49.dp)
            ) {}
        }
    }
}



