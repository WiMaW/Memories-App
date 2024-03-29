package wioleta.wrobel.memoriesapp.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import com.squareup.moshi.JsonClass
import wioleta.wrobel.memoriesapp.ui.theme.card_color_blue
import wioleta.wrobel.memoriesapp.ui.theme.card_color_green
import wioleta.wrobel.memoriesapp.ui.theme.card_color_grey
import wioleta.wrobel.memoriesapp.ui.theme.card_color_pink
import wioleta.wrobel.memoriesapp.ui.theme.card_color_yellow
import wioleta.wrobel.memoriesapp.ui.theme.font_berkshire_swash
import wioleta.wrobel.memoriesapp.ui.theme.font_color_blue
import wioleta.wrobel.memoriesapp.ui.theme.font_color_green
import wioleta.wrobel.memoriesapp.ui.theme.font_color_grey
import wioleta.wrobel.memoriesapp.ui.theme.font_color_pink
import wioleta.wrobel.memoriesapp.ui.theme.font_lobster
import wioleta.wrobel.memoriesapp.ui.theme.font_playball
import wioleta.wrobel.memoriesapp.ui.theme.font_yeseva_one
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Memory(
    val memoryDate: String,
    val memoryTitle: String,
    val memoryDescription: String,
    val memoryImage: String,
    val cardColorType: MemoryCardColors,
    val fontColorType: MemoryFontColor,
    val fontType: MemoryFontFamily
) : Serializable

enum class MemoryCardColors(val color: Color) {
    GREEN(card_color_green),
    PINK(card_color_pink),
    BLUE(card_color_blue),
    YELLOW(card_color_yellow),
    GREY(card_color_grey)
}

enum class MemoryFontColor(val color: Color) {
    GREY(font_color_grey),
    PINK(font_color_pink),
    GREEN(font_color_green),
    BLUE(font_color_blue)
}

enum class MemoryFontFamily(val font: FontFamily) {
    LOBSTER(font_lobster),
    YESEVA(font_yeseva_one),
    BERKSHIRE(font_berkshire_swash),
    PLAYBALL(font_playball)
}