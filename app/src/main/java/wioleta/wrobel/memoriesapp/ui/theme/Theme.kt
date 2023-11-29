package wioleta.wrobel.memoriesapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.core.view.WindowCompat
import wioleta.wrobel.memoriesapp.R

private val DarkColorScheme = darkColorScheme(
    primary = primary_color_light,
    secondary = secondary_color_light,
    tertiary = tertiary_color_light,
    surface = surface_color_light,

)

private val LightColorScheme = lightColorScheme(
    primary = primary_color_light,
    secondary = secondary_color_light,
    tertiary = tertiary_color_light,
)

val font_main_galada = FontFamily(Font(R.font.galada_regular))
val font_main_lobster_bold = FontFamily(Font(R.font.lobster_two_bold))
val font_main_lilita = FontFamily(Font(R.font.lilita_one_regular))
val font_oleo_regular = FontFamily(Font(R.font.oleoscript_regular))
val font_oleo_bold = FontFamily(Font(R.font.oleoscript_bold))
val font_yeseva_one = FontFamily(Font(R.font.yeseva_one_regular))
val font_berkshire_swash = FontFamily(Font(R.font.berkshire_swash_regular))

@Composable
fun MemoriesAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.surface.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
        shapes = Shapes
    )
}