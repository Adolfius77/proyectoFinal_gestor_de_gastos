package ortega.adolfo.gestorgastos.utilities

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.time.LocalDate
import java.time.LocalTime
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

private val formatoCentavos = DecimalFormat("#,##0.00", DecimalFormatSymbols(Locale.US))
private val formatoFechaLarga = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", Locale("es", "MX"))
private val formatoHora = DateTimeFormatter.ofPattern("h:mm a", Locale("es", "MX"))

fun formatoDineroCentavos(valor: Double): String {
    val texto = formatoCentavos.format(abs(valor))
    return if (valor < 0) "-\$$texto" else "\$$texto"
}

fun formatoFechaLarga(fecha: LocalDate): String = fecha.format(formatoFechaLarga)

fun formatoHora(hora: LocalTime): String = hora.format(formatoHora)
