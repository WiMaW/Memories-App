package wioleta.wrobel.memoriesapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = font_main_galada,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        fontFamily = font_oleo_regular,
        color = primary_color_light
    ),
    labelSmall = TextStyle(
        fontFamily = font_oleo_bold,
        fontWeight = FontWeight.Normal,
        fontSize = 19.sp
    ),

)

enum class MemoryFontFamily (val font: FontFamily) {
    OLEO(font_oleo_regular),
    YESEVA(font_yeseva_one),
    BERKSHIRE(font_berkshire_swash),
    LILITA(font_main_lilita)
}