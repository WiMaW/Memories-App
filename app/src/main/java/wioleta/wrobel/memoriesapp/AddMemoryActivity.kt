package wioleta.wrobel.memoriesapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import wioleta.wrobel.memoriesapp.model.Memory
import wioleta.wrobel.memoriesapp.model.MemoryCardColors
import wioleta.wrobel.memoriesapp.model.MemoryFontColor
import wioleta.wrobel.memoriesapp.model.MemoryFontFamily
import wioleta.wrobel.memoriesapp.ui.theme.MemoriesAppTheme
import wioleta.wrobel.memoriesapp.util.StorageOperation
import java.time.LocalDate


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

        var memoryDate: LocalDate by rememberSaveable { mutableStateOf(LocalDate.now()) }
        var memoryTitle by rememberSaveable { mutableStateOf("") }
        var memoryDescription by rememberSaveable { mutableStateOf("") }

        val cardColors = MemoryCardColors.values()
        val fontColors = MemoryFontColor.values()
        val fontsFamily = MemoryFontFamily.values()

        var currentCardColor by rememberSaveable { mutableStateOf(cardColors.first()) }
        var currentFontColor by rememberSaveable { mutableStateOf(fontColors.first()) }
        var currentFontFamily by rememberSaveable { mutableStateOf(fontsFamily.first()) }

        val textStyle: androidx.compose.ui.text.TextStyle = androidx.compose.ui.text.TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            fontFamily = currentFontFamily.font,
            color = currentFontColor.color
        )

        val scrollState = rememberScrollState()

        val calendarState = rememberSheetState()

        CalendarDialog(
            state = calendarState,
            config = CalendarConfig(
                style = CalendarStyle.MONTH
            ),
            selection = CalendarSelection.Date { date ->
                memoryDate = date
            })

        var selectedImage by remember { mutableStateOf<Uri?>(null) }

        val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri ->
                val flag = Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                if (uri != null) {
                    context.contentResolver.takePersistableUriPermission(uri, flag)
                    selectedImage = uri
                }
            })

        Card(
            colors = CardDefaults.cardColors(currentCardColor.color),
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
                .verticalScroll(scrollState)
        ) {
            Column(
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = memoryDate.toString(),
                        onValueChange = {},
                        enabled = false,
                        label = {
                            Text(
                                text = stringResource(id = R.string.memory_date),
                                style = MaterialTheme.typography.labelMedium,
                                textAlign = TextAlign.Center,
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            disabledBorderColor = MaterialTheme.colorScheme.primary,
                            textColor = Color.Gray
                        ),
                        textStyle = textStyle,
                        modifier = Modifier
                            .height(66.dp)
                            .width(150.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    ElevatedButton(
                        onClick = { calendarState.show() },
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                        shape = MaterialTheme.shapes.large,
                        modifier = Modifier.size(30.dp),
                        contentPadding = PaddingValues(1.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "add_icon",
                            tint = Color.White
                        )
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    value = memoryTitle,
                    onValueChange = { memoryTitle = it },
                    label = {
                        Text(
                            text = stringResource(id = R.string.memory_title),
                            style = MaterialTheme.typography.labelMedium,
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                        textColor = Color.Gray
                    ),
                    textStyle = textStyle,
                    modifier = Modifier
                        .height(66.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    value = memoryDescription,
                    onValueChange = { memoryDescription = it },
                    label = {
                        Text(
                            text = stringResource(id = R.string.memory_description),
                            style = MaterialTheme.typography.labelMedium,
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                        textColor = Color.Gray
                    ),
                    textStyle = textStyle,
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = if (selectedImage == null) "" else selectedImage.toString(),
                        enabled = false,
                        onValueChange = {},
                        label = {
                            Text(
                                text = stringResource(id = R.string.add_image),
                                style = MaterialTheme.typography.labelMedium,
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            disabledBorderColor = MaterialTheme.colorScheme.primary,
                            textColor = Color.Gray
                        ),
                        textStyle = textStyle,
                        modifier = Modifier
                            .height(66.dp)
                            .width(150.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    ElevatedButton(
                        onClick = {
                            singlePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                        shape = MaterialTheme.shapes.large,
                        modifier = Modifier.size(30.dp),
                        contentPadding = PaddingValues(1.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "add_icon",
                            tint = Color.White
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                    items(items = cardColors) { cardColor ->
                        ElevatedButton(
                            onClick = { currentCardColor = cardColor },
                            colors = ButtonDefaults.buttonColors(containerColor = cardColor.color),
                            shape = MaterialTheme.shapes.large,
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                            modifier = Modifier.size(49.dp),
                        ) {}
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(17.dp)) {
                    items(items = fontColors) { fontColor ->
                        ElevatedButton(
                            onClick = { currentFontColor = fontColor },
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
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(items = fontsFamily) { fontFamily ->
                        ElevatedButton(
                            onClick = { currentFontFamily = fontFamily },
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
                Row() {
                    ElevatedButton(
                        onClick = {
                            val intent = Intent(context, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        },
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                        shape = MaterialTheme.shapes.large,
                        modifier = Modifier.width(125.dp)
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
                            val currentMemoryImage = selectedImage.toString()
                            val currentMemoryDate = memoryDate.toString()
                            val memory = Memory(
                                memoryDate = currentMemoryDate,
                                memoryTitle = memoryTitle,
                                memoryDescription = memoryDescription,
                                memoryImage = currentMemoryImage,
                                cardColorType = currentCardColor,
                                fontColorType = currentFontColor,
                                fontType = currentFontFamily
                            )
                            StorageOperation.writeMemory(context, memory)

                            val intent = Intent(context, MainActivity::class.java)
                            intent.putExtra("memory", memory)
                            startActivity(intent)
                            finish()
                        },
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                        shape = MaterialTheme.shapes.large,
                        modifier = Modifier.width(125.dp)
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
}

