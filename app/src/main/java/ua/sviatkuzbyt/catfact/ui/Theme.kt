package ua.sviatkuzbyt.catfact.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ua.sviatkuzbyt.catfact.R

@Immutable
data class ThemeColors(
    val primary: Color,
    val surface: Color,
    val background: Color,
    val text: Color,
    val white: Color,
    val drag: Color
) {
    companion object{
        fun getLightColors() = ThemeColors(
            primary = Color(0xFF1E90FF),
            surface = Color(0xFFE9F4FF),
            background = Color(0xFFFFFFFF),
            text = Color(0xFF121212),
            white = Color(0xFFFFFFFF),
            drag = Color(0xFFBFBFBF)
        )

        fun getDarkColors() = ThemeColors(
            primary = Color(0xFF1E90FF),
            surface = Color(0xFF142A3F),
            background = Color(0xFF121212),
            text = Color(0xFFFFFFFF),
            white = Color(0xFFFFFFFF),
            drag = Color(0xFF4D4D4D)
        )
    }
}

data class ThemeTypes(
    val basic: TextStyle,
    val basicBold: TextStyle,
    val big: TextStyle,
    val bigBold: TextStyle,
    val white: TextStyle
){
    companion object{
        private val fontFamily = FontFamily(
            Font(R.font.roboto_regular, FontWeight.Normal),
            Font(R.font.roboto_bold, FontWeight.Bold)
        )

        fun getTypographies(colors: ThemeColors) = ThemeTypes(
            basic = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = colors.text
            ),
            basicBold = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = colors.text
            ),
            big = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                color = colors.text
            ),
            bigBold = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = colors.text
            ),
            white = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = colors.white
            )
        )
    }
}

val LocalThemeColors = staticCompositionLocalOf<ThemeColors> { error("No colors yet") }
val LocalThemeTypes = staticCompositionLocalOf<ThemeTypes> { error("No types yet") }

@Composable
fun CatFactTheme(
    isDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors =
        if(isDark) ThemeColors.getDarkColors()
        else ThemeColors.getLightColors()

    val type = ThemeTypes.getTypographies(colors)

    CompositionLocalProvider(
        LocalThemeColors provides colors, LocalThemeTypes provides type
    ){
        content()
    }
}

object Theme {
    val colors: ThemeColors
        @Composable
        get() = LocalThemeColors.current

    val types: ThemeTypes
        @Composable
        get() = LocalThemeTypes.current
}