package ortega.adolfo.gestorgastos.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Azul = Color(0xFF315E86)
val Naranja = Color(0xFFDD8B18)
val Rosa = Color(0xFFB94379)
val Morado = Color(0xFF7D5BC2)
val VerdeWacho = Color(0xFF14241E)

data class ColoresApp(
    val esOscuro: Boolean,
    val fondo: Color,
    val superficie: Color,
    val superficieAlta: Color,
    val borde: Color,
    val bordeControl: Color,
    val textoPrincipal: Color,
    val textoSecundario: Color,
    val placeholder: Color,
    val verdePrimario: Color,
    val verdeAcento: Color,
    val verdeSuave: Color,
    val naranjaSuave: Color,
    val rojoError: Color,
    val rojoSuave: Color,
    val indicadorNav: Color,
    val iconoNavActivo: Color,
    val miniIngresoFondo: Color,
    val miniIngresoTexto: Color,
    val miniGastoFondo: Color,
    val miniGastoTexto: Color,
    val pildoraBalance: Color
)

val ColoresClaros = ColoresApp(
    esOscuro = false,
    fondo = Color(0xFFEEF0EC),
    superficie = Color.White,
    superficieAlta = Color(0xFFEEF0EC),
    borde = Color(0xFFDADDD9),
    bordeControl = Color(0xFF14201A),
    textoPrincipal = Color(0xFF14201A),
    textoSecundario = Color(0xFF56625B),
    placeholder = Color(0xFF8D928E),
    verdePrimario = Color(0xFF0E6B4F),
    verdeAcento = Color(0xFF0E6B4F),
    verdeSuave = Color(0xFFD4F5E0),
    naranjaSuave = Color(0xFFFDE4D3),
    rojoError = Color(0xFFBF3833),
    rojoSuave = Color(0xFFFDE4E1),
    indicadorNav = Color(0xFFD4F5E0),
    iconoNavActivo = Color(0xFF0E6B4F),
    miniIngresoFondo = Color(0xFFD4F5E0),
    miniIngresoTexto = Color(0xFF0E6B4F),
    miniGastoFondo = Color(0xFFFDE4D3),
    miniGastoTexto = Color(0xFFBF3833),
    pildoraBalance = Color.White.copy(alpha = 0.15f)
)


val ColoresOscuros = ColoresApp(
    esOscuro = true,
    fondo = Color(0xFF121714),
    superficie = Color(0xFF1F2722),
    superficieAlta = Color(0xFF272F2A),
    borde = Color(0xFF414D45),
    bordeControl = Color(0xFF8B9990),
    textoPrincipal = Color(0xFFECF0ED),
    textoSecundario = Color(0xFFABB7AF),
    placeholder = Color(0xFF5C6661),
    verdePrimario = Color(0xFF0E6B4F),
    verdeAcento = Color(0xFF1A7A5E),
    verdeSuave = Color(0xFF272F2A),
    naranjaSuave = Color(0xFF15956E),
    rojoError = Color(0xFFFF1E22),
    rojoSuave = Color(0xFF3A2324),
    indicadorNav = Color(0xFFE3EFE9),
    iconoNavActivo = Color(0xFF00785F),
    miniIngresoFondo = Color(0xFF15956E),
    miniIngresoTexto = Color.White,
    miniGastoFondo = Color(0xFF15956E),
    miniGastoTexto = Color.White,
    pildoraBalance = Color(0xFF15956E)
)

val LocalColoresApp = staticCompositionLocalOf { ColoresClaros }

val Colores: ColoresApp
    @Composable @ReadOnlyComposable get() = LocalColoresApp.current

val FondoClaro: Color @Composable @ReadOnlyComposable get() = LocalColoresApp.current.fondo
val Superficie: Color @Composable @ReadOnlyComposable get() = LocalColoresApp.current.superficie
val SuperficieAlta: Color @Composable @ReadOnlyComposable get() = LocalColoresApp.current.superficieAlta
val BordeGris: Color @Composable @ReadOnlyComposable get() = LocalColoresApp.current.borde
val BordeControl: Color @Composable @ReadOnlyComposable get() = LocalColoresApp.current.bordeControl
val TextoPrincipal: Color @Composable @ReadOnlyComposable get() = LocalColoresApp.current.textoPrincipal
val TextoSecundario: Color @Composable @ReadOnlyComposable get() = LocalColoresApp.current.textoSecundario
val GrisPlaceholder: Color @Composable @ReadOnlyComposable get() = LocalColoresApp.current.placeholder
val VerdePrimario: Color @Composable @ReadOnlyComposable get() = LocalColoresApp.current.verdePrimario
val VerdeAcento: Color @Composable @ReadOnlyComposable get() = LocalColoresApp.current.verdeAcento
val VerdeSuave: Color @Composable @ReadOnlyComposable get() = LocalColoresApp.current.verdeSuave
val NaranjaSuave: Color @Composable @ReadOnlyComposable get() = LocalColoresApp.current.naranjaSuave
val RojoError: Color @Composable @ReadOnlyComposable get() = LocalColoresApp.current.rojoError
val RojoSuave: Color @Composable @ReadOnlyComposable get() = LocalColoresApp.current.rojoSuave
