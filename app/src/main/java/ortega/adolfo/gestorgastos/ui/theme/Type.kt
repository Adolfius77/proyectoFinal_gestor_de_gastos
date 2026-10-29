package ortega.adolfo.gestorgastos.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.R

val Nunito = FontFamily(
    Font(R.font.nunito_regular),
    Font(R.font.nunito_extrabold)
)
val Manrope = FontFamily(
    Font(R.font.manrope_regular),
    Font(R.font.manrope_medium),
    Font(R.font.manrope_semibold)
)
val SpaceGrotesk = FontFamily(
    Font(R.font.spacegrotesk_medium, FontWeight.Medium),
    Font(R.font.spacegrotesk_bold, FontWeight.Bold)
)

val Typography = Typography(
    // Titulos grandes
    headlineLarge = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.Bold, fontSize = 28.sp),
    headlineMedium = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.Bold, fontSize = 24.sp),

    // Montos y cifras del resumen
    displaySmall = TextStyle(fontFamily = SpaceGrotesk, fontWeight = FontWeight.Bold, fontSize = 32.sp),

    // Texto general, etiquetas y botones
    bodyLarge = TextStyle(fontFamily = Manrope, fontWeight = FontWeight.Normal, fontSize = 16.sp),
    bodyMedium = TextStyle(fontFamily = Manrope, fontWeight = FontWeight.Normal, fontSize = 14.sp),
    labelLarge = TextStyle(fontFamily = Manrope, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
)
