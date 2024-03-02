package com.tzeyi.movies.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tzeyi.movies.R

val robotoFamily = FontFamily(
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto_black, FontWeight.Black),
)

private val defaultTypography = Typography()
val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = robotoFamily),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = robotoFamily),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = robotoFamily),
    
    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = robotoFamily),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = robotoFamily),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = robotoFamily),
    
    titleLarge = defaultTypography.titleLarge.copy(fontFamily = robotoFamily),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = robotoFamily),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = robotoFamily),
    
    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = robotoFamily),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = robotoFamily),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = robotoFamily),
    
    labelLarge = defaultTypography.labelLarge.copy(fontFamily = robotoFamily),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = robotoFamily),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = robotoFamily)
)
