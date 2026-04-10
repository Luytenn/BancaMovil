package com.example.bancamovil.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.bancamovil.R


data class AppColorScheme(
    val background: Color,
    val onBackground: Color,
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color
)

data class AppTypography(
    val h1: TextStyle,
    val PreloBoldBlueDisplay32: TextStyle,
    val PreloBoldBlueDisplay18: TextStyle,
    val PreloMediumGrayDisplay16: TextStyle,
    val PreloMediumGrayDisplay14: TextStyle
)

data class AppSize(
    val large: Dp,
    val medium: Dp,
    val normal: Dp,
    val small: Dp
)

data class DisplaySize(
    val text_size_xxxs: TextUnit = 4.sp,
    val text_size_xxs: TextUnit = 8.sp,
    val text_size_xs: TextUnit = 10.sp,
    val text_size_s: TextUnit = 12.sp,
    val text_size_m: TextUnit = 14.sp,
    val text_size_l: TextUnit = 16.sp,
    val text_size_xl: TextUnit = 18.sp,
    val text_size_xxl: TextUnit = 20.sp,
    val text_size_xxxl: TextUnit = 22.sp,
    val text_size_xxxxl: TextUnit = 24.sp,
    val text_size_xxxxxxl: TextUnit = 28.sp,
    val text_size_xxxxxxxxl: TextUnit = 32.sp,
    val text_size_xxxxxxxxxl: TextUnit = 40.sp
)




data class CustomTypography(
    val h1: TextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp
    ),
    val body1: TextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    val PreloBoldBlueDisplay32: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R. font.prelo_bold)),
        fontWeight = FontWeight.Normal,
        fontSize = DisplaySize().text_size_xxxxxxxxl
    ),

    val PreloBoldBlackDisplay28: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.prelo_bold)),
        fontWeight = FontWeight.Normal,
        fontSize = DisplaySize().text_size_xxxxxxl,
        color = Color.Black
    ),

    val PreloBoldBlackDisplay20: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.prelo_bold)),
        fontWeight = FontWeight.Normal,
        fontSize = DisplaySize().text_size_xxl,
        color = Color.Black
    ),

    val PreloBoldBlueDisplay18: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.prelo_bold)),
        fontWeight = FontWeight.Normal,
        fontSize = DisplaySize().text_size_xl,
        color = blue500
    ),
    val PreloMediumBlueDisplay12: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.prelo_medium)),
        fontWeight = FontWeight.Normal,
        fontSize = DisplaySize().text_size_s,
        color = blue500
    ),
    val PreloMediumGrayDisplay16: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.prelo_medium)),
        fontWeight = FontWeight.Normal,
        fontSize = DisplaySize().text_size_l,
        color = gray500
    ),
    val PreloMediumGrayDisplay14: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.prelo_medium)),
        fontWeight = FontWeight.Normal,
        fontSize = DisplaySize().text_size_m,
        color = gray500
    ),

    val PreloMediumBlackDisplay28: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.prelo_medium)),
        fontWeight = FontWeight.Normal,
        fontSize = DisplaySize().text_size_xxxxxxl,
        color = Color.Black
    ),

    val PreloMediumBlackDisplay20: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.prelo_medium)),
        fontWeight = FontWeight.Normal,
        fontSize = DisplaySize().text_size_xxl,
        color = Color.Black
    ),

    val PreloMediumBlackDisplay18: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.prelo_medium)),
        fontWeight = FontWeight.Normal,
        fontSize = DisplaySize().text_size_xl,
        color = Color.Black
    ),

    val PreloMediumBlackDisplay16: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.prelo_medium)),
        fontWeight = FontWeight.Normal,
        fontSize = DisplaySize().text_size_l,
        color = Color.Black
    ),

    val PreloMediumBlackDisplay14: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.prelo_medium)),
        fontWeight = FontWeight.Normal,
        fontSize = DisplaySize().text_size_m,
        color = Color.Black
    )

)

val LocalAppTypography = staticCompositionLocalOf {
    CustomTypography()
}


val LocalAppSize = staticCompositionLocalOf {
    AppSize(
        large = Dp.Unspecified,
        medium = Dp.Unspecified,
        normal = Dp.Unspecified,
        small = Dp.Unspecified
    )
}