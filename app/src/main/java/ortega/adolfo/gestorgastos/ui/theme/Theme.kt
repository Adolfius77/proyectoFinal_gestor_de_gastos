package ortega.adolfo.gestorgastos.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private fun esquemaMaterial(c: ColoresApp): ColorScheme {
    val base = if (c.esOscuro) darkColorScheme() else lightColorScheme()
    return base.copy(
        primary = c.verdePrimario,
        onPrimary = Color.White,
        primaryContainer = c.verdeSuave,
        onPrimaryContainer = c.verdeAcento,
        secondaryContainer = c.indicadorNav,
        onSecondaryContainer = c.iconoNavActivo,
        background = c.fondo,
        onBackground = c.textoPrincipal,
        surface = c.superficie,
        onSurface = c.textoPrincipal,
        surfaceVariant = c.superficieAlta,
        onSurfaceVariant = c.textoSecundario,
        surfaceContainerLowest = c.superficie,
        surfaceContainerLow = c.superficie,
        surfaceContainer = c.superficie,
        surfaceContainerHigh = c.superficie,
        surfaceContainerHighest = c.superficieAlta,
        outline = c.bordeControl,
        outlineVariant = c.borde,
        error = c.rojoError,
        errorContainer = c.rojoSuave,
        onError = Color.White
    )
}

@Composable
fun GestorGastosTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colores = if (darkTheme) ColoresOscuros else ColoresClaros

    val vista = LocalView.current
    if (!vista.isInEditMode) {
        SideEffect {
            val ventana = (vista.context as? Activity)?.window ?: return@SideEffect
            WindowCompat.getInsetsController(ventana, vista).apply {
                isAppearanceLightStatusBars = !darkTheme
                isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    CompositionLocalProvider(LocalColoresApp provides colores) {
        MaterialTheme(
            colorScheme = esquemaMaterial(colores),
            typography = Typography,
            content = content
        )
    }
}
