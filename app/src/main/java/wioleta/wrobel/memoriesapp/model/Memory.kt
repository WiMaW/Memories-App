package wioleta.wrobel.memoriesapp.model

import android.media.Image
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import wioleta.wrobel.memoriesapp.ui.theme.card_color_blue
import wioleta.wrobel.memoriesapp.ui.theme.card_color_green
import wioleta.wrobel.memoriesapp.ui.theme.card_color_grey
import wioleta.wrobel.memoriesapp.ui.theme.card_color_pink
import wioleta.wrobel.memoriesapp.ui.theme.card_color_yellow
import wioleta.wrobel.memoriesapp.ui.theme.font_berkshire_swash
import wioleta.wrobel.memoriesapp.ui.theme.font_color_blue
import wioleta.wrobel.memoriesapp.ui.theme.font_color_green
import wioleta.wrobel.memoriesapp.ui.theme.font_color_grey
import wioleta.wrobel.memoriesapp.ui.theme.font_main_lilita
import wioleta.wrobel.memoriesapp.ui.theme.font_oleo_regular
import wioleta.wrobel.memoriesapp.ui.theme.font_yeseva_one
import wioleta.wrobel.memoriesapp.ui.theme.primary_color_light
import java.io.DataInput

data class Memory(
    val memoryDate: DataInput,
    val memoryTitle: String,
    val memoryDescription: String,
    val memoryImage: Image,
    val cardColorType: MemoryCardColors,
    val fontColorType: MemoryFontColor,
    
)

enum class MemoryCardColors(val color: Color) {
    GREEN(card_color_green),
    PINK(card_color_pink),
    BLUE(card_color_blue),
    YELLOW(card_color_yellow),
    GREY(card_color_grey)
}

enum class MemoryFontColor(val color: Color) {
    PURPULE(primary_color_light),
    GREY(font_color_grey),
    GREEN(font_color_green),
    BLUE(font_color_blue)
}

enum class MemoryFontFamily (val font: FontFamily) {
    OLEO(font_oleo_regular),
    YESEVA(font_yeseva_one),
    BERKSHIRE(font_berkshire_swash),
    LILITA(font_main_lilita)
}