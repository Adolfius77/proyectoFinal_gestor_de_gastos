package ortega.adolfo.gestorgastos.utilities

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.abs

private val formatoMiles = DecimalFormat("#,##0", DecimalFormatSymbols(Locale.US))
private val formatoFecha = DateTimeFormatter.ofPattern("dd/MMM/yyyy", Locale("es", "MX"))


fun formatoDinero(valor: Double): String {
    val texto = formatoMiles.format(abs(valor))
    return if (valor < 0) "-\$$texto" else "\$$texto"
}


fun formatoPorcentaje(valor: Float): String =
    String.format(Locale.US, "%.1f%%", valor * 100)


fun formatoFechaCorta(fecha: LocalDate): String =
    fecha.format(formatoFecha).replace(".", "")
