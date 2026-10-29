package ortega.adolfo.gestorgastos.view.components

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import ortega.adolfo.gestorgastos.ui.theme.SpaceGrotesk


private val patronNumero = Regex("""[+\-]?\$?\d[\d,.]*%?""")


fun textoConNumeros(
    texto: String,
    fuenteNumeros: FontFamily = SpaceGrotesk
): AnnotatedString = buildAnnotatedString {
    var ultimo = 0
    patronNumero.findAll(texto).forEach { coincidencia ->
        append(texto.substring(ultimo, coincidencia.range.first))
        withStyle(SpanStyle(fontFamily = fuenteNumeros)) {
            append(coincidencia.value)
        }
        ultimo = coincidencia.range.last + 1
    }
    append(texto.substring(ultimo))
}
