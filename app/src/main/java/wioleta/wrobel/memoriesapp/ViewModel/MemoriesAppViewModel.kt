package wioleta.wrobel.memoriesapp.ViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import wioleta.wrobel.memoriesapp.model.Memory

class MemoriesAppViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow(MemoriesAppUiState())
    val uiState: StateFlow<MemoriesAppUiState> = _uiState

    fun navigateToSingleMemory(currentMemory: Memory) {
        _uiState.update {
            it.copy(isCardClicked = true, memory = currentMemory)
        }
    }

    fun navigateToMemoryList() {
        _uiState.update {
            it.copy(isCardClicked = false)
        }
    }

}

data class MemoriesAppUiState(
//    val memoriesList: List <Memory> = emptyList(),
//    val memoriesListLength: Int = 0,
    val memory: Memory? = null,
    val isCardClicked: Boolean = false,
//    val currentCardColor: MemoryCardColors = MemoryCardColors.values().first(),
//    val currentFontFamily: MemoryFontFamily = MemoryFontFamily.values().first(),
//    val currentFontColor: MemoryFontColor = MemoryFontColor.values().first()
)