package wioleta.wrobel.memoriesapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import wioleta.wrobel.memoriesapp.ViewModel.MemoriesAppViewModel
import wioleta.wrobel.memoriesapp.model.Memory
import wioleta.wrobel.memoriesapp.ui.theme.MemoriesAppTheme
import wioleta.wrobel.memoriesapp.util.StorageOperation

class MainActivity : ComponentActivity() {

    private var memoriesList = mutableListOf<Memory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        memoriesList = StorageOperation.readMemoryList(this).toMutableList()

        val memory = intent.getSerializableExtra("memory") as? Memory
        memory?.let {
            memoriesList.add(it)
            StorageOperation.writeMemoryList(this, memoriesList)
        }
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
        val memoriesListLength by remember { mutableStateOf(memoriesList.size) }

        if (memoriesListLength > 0) {
            DisplayMemoriesScreen(context)
        } else {
            MainScreenFirstTimeRunningApp(context)
        }
    }

    @Composable
    fun MainScreenFirstTimeRunningApp(context: Context) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
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

    @Composable
    fun DisplayMemoriesScreen(
        context: Context,
    ) {

        val viewModel: MemoriesAppViewModel = viewModel()
        val uiState by viewModel.uiState.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            if (!uiState.isCardClicked) {
                DisplayTitle()
            }
            Box(modifier = Modifier.fillMaxSize()) {
                DisplayMemoriesListOrSingleMemory(memoriesList = memoriesList)

                if (!uiState.isCardClicked) {
                    FloatingActionButton(
                        containerColor = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.align(Alignment.BottomCenter),
                        onClick = {
                            val intent = Intent(context, AddMemoryActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "add icon",
                            tint = MaterialTheme.colorScheme.surface,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun DisplayTitle() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            Text(
                text = stringResource(id = R.string.memories_list),
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary
            )
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.filter_list),
                    contentDescription = "filter icon",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(35.dp)
                )
            }
        }
    }

    @Composable
    fun DisplayMemoriesListOrSingleMemory(
        memoriesList: List<Memory>,
    ) {

        val viewModel: MemoriesAppViewModel = viewModel()
        val uiState by viewModel.uiState.collectAsState()

        if (!uiState.isCardClicked) {
            LazyColumn() {
                items(memoriesList) { memory ->
                    Card(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .height(100.dp)
                            .clickable {
                                viewModel.navigateToSingleMemory(memory)
                            },
                        colors = CardDefaults.cardColors(containerColor = memory.cardColorType.color),
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .padding(4.dp)
                            ) {
                                Text(
                                    text = memory.memoryDate,
                                    fontFamily = memory.fontType.font,
                                    fontSize = 16.sp,
                                    color = memory.fontColorType.color,
                                    overflow = TextOverflow.Ellipsis,
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = memory.memoryTitle,
                                    fontFamily = memory.fontType.font,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = memory.fontColorType.color,
                                )
                            }
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "clear_icon",
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .clickable { StorageOperation },
                                tint = Color.White
                                )
                            Icon(
                                imageVector = Icons.Filled.Edit,
                                contentDescription = "edit_icon",
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .clickable { },
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        } else {
            DisplaySingleMemory(currentMemory = uiState.memory)
        }
    }

    @Composable
    fun DisplaySingleMemory(currentMemory: Memory?) {

        LocalContext.current
        val imageToDisplay = currentMemory?.memoryImage?.toUri()

        if (currentMemory != null) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                colors = CardDefaults.cardColors(containerColor = currentMemory.cardColorType.color)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = currentMemory.memoryDate,
                            fontFamily = currentMemory.fontType.font,
                            color = currentMemory.fontColorType.color,
                            fontSize = 16.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Column(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                                .height(250.dp),
                            contentAlignment = Alignment.Center
                        )
                        {
                            LoadImageFromURi(imageToDisplay = imageToDisplay)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = currentMemory.memoryTitle,
                            fontFamily = currentMemory.fontType.font,
                            color = currentMemory.fontColorType.color,
                            fontSize = 26.sp
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Box(modifier = Modifier.fillMaxSize())
                        {
                            Text(
                                text = currentMemory.memoryDescription,
                                fontFamily = currentMemory.fontType.font,
                                color = currentMemory.fontColorType.color,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Justify
                            )
                        }
                    }
                }
            }
        } else { }
    }

    @Composable
    fun LoadImageFromURi(imageToDisplay: Uri?) {
        if (imageToDisplay != null) {
            contentResolver.takePersistableUriPermission(
                imageToDisplay,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }
        AsyncImage(
            model = imageToDisplay,
            contentDescription = "added photo",
            contentScale = ContentScale.Crop
        )
    }
}




