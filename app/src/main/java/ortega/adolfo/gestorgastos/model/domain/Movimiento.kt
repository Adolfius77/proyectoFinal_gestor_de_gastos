package ortega.adolfo.gestorgastos.model.domain

import java.time.LocalDate
import java.time.LocalTime

// TipoMovimiento esta en TipoMovimiento.kt

enum class MetodoPago(val etiqueta: String) {
    EFECTIVO("Efectivo"),
    TARJETA("Tarjeta"),
    TRANSFERENCIA("Transferencia");

    companion object {
        // Convierte el texto del formulario ("Tarjeta") al enum
        fun desdeEtiqueta(texto: String): MetodoPago =
            entries.find { it.etiqueta.equals(texto, ignoreCase = true) } ?: EFECTIVO
    }
}

// Tarjeta con la que se pago, por ejemplo Debito terminacion 5778
data class Tarjeta(
    val tipo: String,
    val terminacion: String
) {
    companion object {
        // Convierte "BBVA 7789" en Tarjeta(tipo = "BBVA", terminacion = "7789")
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

// Un gasto o un ingreso. Lo usan la pantalla principal, el detalle,
// el resumen y el registro de movimientos.
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
