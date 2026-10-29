package ortega.adolfo.gestorgastos.model.domain

import java.time.LocalDate
import java.time.LocalTime


enum class MetodoPago(val etiqueta: String) {
    EFECTIVO("Efectivo"),
    TARJETA("Tarjeta"),
    TRANSFERENCIA("Transferencia");

    companion object {
        fun desdeEtiqueta(texto: String): MetodoPago =
            entries.find { it.etiqueta.equals(texto, ignoreCase = true) } ?: EFECTIVO
    }
}

data class Tarjeta(
    val tipo: String,
    val terminacion: String
) {
    companion object {
        fun desdeTexto(texto: String): Tarjeta {
            val limpio = texto.trim()
            val ultimo = limpio.substringAfterLast(" ", "")
            return if (ultimo.isNotEmpty() && ultimo.all { it.isDigit() })
                Tarjeta(limpio.substringBeforeLast(" "), ultimo)
            else
                Tarjeta(limpio, "")
        }
    }
}

data class Movimiento(
    val id: Int,
    val descripcion: String,
    val monto: Double,
    val fecha: LocalDate,
    val categoria: String,
    val tipo: TipoMovimiento,
    val metodoPago: MetodoPago,
    val tarjeta: Tarjeta? = null,
    val hora: LocalTime? = null,
    val ubicacion: String? = null,
    val nota: String? = null,
    val fotoRecibo: String? = null
)
