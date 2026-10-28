package ortega.adolfo.gestorgastos.view.components

import java.util.Locale
import kotlin.math.abs

// Da este formato de 12800.0 a "$12,800"
fun Double.comoMoneda(): String {
    val texto = "$" + String.format(Locale.US, "%,.0f", abs(this))
    return if (this < 0) "-" + texto else texto
}
